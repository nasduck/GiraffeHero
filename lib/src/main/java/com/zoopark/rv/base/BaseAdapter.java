package com.zoopark.rv.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zoopark.rv.empty.OnEmptyViewListener;
import com.zoopark.rv.loadmore.DefaultLoadMoreView;
import com.zoopark.rv.loadmore.LoadMoreView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected ProviderDelegate mProviderDelegate;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private int preItemCount;

    // Empty View
    private Boolean isShowEmptyView;
    private FrameLayout mEmptyLayout;
    private OnEmptyViewListener mEmptyViewClickListener;

    // Load More
    private boolean mIsLoadMoreEnable;
    private boolean mIsLoadMoreLoading;
    private LoadMoreView mLoadMoreView;
    private int mPreLoadNumber = 1;
    private BaseAdapterLoadMoreListener mLoadMoreListener;

    // View Type
    public static final int EMPTY_VIEW_TYPE = -1;
    public static final int LOAD_MORE_VIEW_TYPE = -2;

    public BaseAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mProviderDelegate = new ProviderDelegate();
        isShowEmptyView = false;
        mIsLoadMoreEnable = false;
        mIsLoadMoreLoading = false;
    }

    /**
     * 用于adapter构造函数完成参数的赋值后调用
     */
    public void finishInitialize() {
        registerItemProvider();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW_TYPE:
                return createBaseViewHolder(mEmptyLayout);
            case LOAD_MORE_VIEW_TYPE:
                return createLoadingViewHolder(parent);
            default:
                BaseProvider provider = mProviderDelegate.getProvider(viewType);
                return new BaseViewHolder(mLayoutInflater.inflate(provider.getLayout(), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Do not move position, need to change before LoadMoreView binding
        autoLoadMore(position);

        switch (holder.getItemViewType()) {
            case EMPTY_VIEW_TYPE:
                break;
            case LOAD_MORE_VIEW_TYPE:
                mLoadMoreView.convert((BaseViewHolder)holder);
                break;
            default:
                IndexPath indexPath = getIndexPath(position);
                mProviderDelegate.getProvider(holder.getItemViewType()).onBind((BaseViewHolder)holder, indexPath);
                break;
        }
    }

    /**
     * todo 是否有必要添加payloads
     *
     * @param holder
     * @param position
     * @param payloads
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowEmptyView) {
            return EMPTY_VIEW_TYPE;
        } else {
            if (mIsLoadMoreEnable && position == getTotalItemCount()) {
                return LOAD_MORE_VIEW_TYPE;
            } else {
                return getIndexPath(position).getProviderSection();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (isShowEmptyView) {
            return 1;
        } else {
            int count = getTotalRowCount();
            this.preItemCount = count;
            if (mIsLoadMoreEnable) count += 1;
            return count; // 注意这里返回的是总的 item 数量
        }
    }

    /**
     * Return the total number of items (laodview/header/footer not included)
     *
     * @return
     */
    public int getTotalRowCount() {
        int count = 0;
        for (int i = 0; i < getSectionNumber(); i++) {
            count += getRowCountInSection(i);
        };
        return count;
    }

    /**
     * Return the total number of items (header/footer included)
     *
     * @return
     */
    public int getTotalItemCount() {
        int count = 0;
        for (int i = 0; i < getSectionNumber(); i++) {
            count += getTotalItemCountInSection(i);
        };
        return count;
    }

    //** Abstract ********************************************************************************//

    /**
     * 在 Adapter 注册 Provider 的种类
     */
    public abstract void registerItemProvider();

    //** Public ******

    /**
     * 获得 Section 的数量
     *
     * @return
     */
    public int getSectionNumber() {
        return mProviderDelegate.getSectionList().size();
    };

    /**
     * 获得对应 Section 中 Item 的数量
     *
     * @param section
     * @return
     */
    public int getRowCountInSection(int section) {
        return mProviderDelegate.getSection(section).getItemCount();
    };

    /**
     * 获得对应 Section 中 Item/Header/Footer 总数量
     *
     * @param section
     * @return
     */
    public int getTotalItemCountInSection(int section) {
        BaseItemProvider provider = mProviderDelegate.getSection(section);
        int count = provider.getItemCount();
        if (provider.hasHeader()) count++;
        return count;
    };

    /**
     * 获取除某一 Section 之外的 Item 的数量
     *
     * @param section
     * @return
     */
    public int getExtraRowNumberInSection(int section) {
        int count = 0;
        for (int i = 0; i < getSectionNumber(); i++) {
            if (i != section) {
                count += getTotalItemCountInSection(i);
            }
        }
        return count;
    }

    /**
     * Whether specified item has header
     *
     * @param section section
     * @return true if has header
     */
    public boolean isSectionHasHeader(int section) {
        return mProviderDelegate.getSection(section).hasHeader();
    }

    /**
     * 获得 position 对应的 indexPath
     *
     * @param position 列表中的位置
     * @return
     */
    public IndexPath getIndexPath(int position) {
        IndexPath indexPath = new IndexPath();

        int itemCount = 0;
        int lastItemCount;
        int sectionCount = getSectionNumber();

        for (int i = 0; i < sectionCount; i++) {

            lastItemCount = itemCount;

            int sectionTotalItemCount = getTotalItemCountInSection(i);
            itemCount += sectionTotalItemCount;

            BaseItemProvider section = mProviderDelegate.getSection(i);

            if (itemCount > position) {
                indexPath.setSection(i);
                if (position - lastItemCount == 0) { // if first one
                    if (mProviderDelegate.getSection(i).hasHeader()) { // if first one is header
                        indexPath.setRow(-1);
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section.getHeader()));
                    } else {
                        indexPath.setRow(position - lastItemCount);
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section));
                    }
                } else if (position - lastItemCount == sectionTotalItemCount) { // if last one
                    if (mProviderDelegate.getSection(i).hasFooter()) { // if last one is footer
                        indexPath.setRow(-2);
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section.getFooter()));
                    } else {
                        if (mProviderDelegate.getSection(i).hasHeader()) {
                            indexPath.setRow(position - lastItemCount - 1);
                        } else {
                            indexPath.setRow(position - lastItemCount);
                        }
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section));
                    }
                } else {
                    if (mProviderDelegate.getSection(i).hasHeader()) {
                        indexPath.setRow(position - lastItemCount - 1);
                    } else {
                        indexPath.setRow(position - lastItemCount);
                    }
                    indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section));
                }
                return indexPath;
            }
        }

        throw new RuntimeException("IndexPath not available with position " + position);
    }

    public int getSectionHeaderFooterCount(int section) {
        int count = 0;
        BaseItemProvider provider = mProviderDelegate.getSection(section);
        if (provider.hasHeader()) count++;
        if (provider.hasFooter()) count++;
        return count;
    }

    /**
     * get section with specified position
     *
     * @param pos position in the list
     * @return
     */
    public int getSection(int pos) {
        int count = 0;
        for (int i = 0; i < getSectionNumber(); i ++) {
            count += getTotalItemCountInSection(i);

            if (count > pos) {
                return i;
            }
        }

        throw new RuntimeException("Section not available with position " + pos);
    }

    /**
     * Update specified section
     *
     * @param section section
     */
    public void notifySectionChanged(int section) {
        int pos = 0;
        for (int i = 0; i < section; i++) {
            pos += getTotalItemCountInSection(i);
        }

        if (isSectionHasHeader(section)) pos++;

        // section item 变少
        int sectionPreItemCount = this.preItemCount - getExtraRowNumberInSection(section);
        if (getTotalItemCountInSection(section) < sectionPreItemCount) {
            this.notifyItemRangeRemoved(pos, sectionPreItemCount);
        } else {
            this.notifyItemRangeChanged(pos, getRowCountInSection(section));
        }
    }

    /**
     * Update specified row
     *
     * @param indexPath item location
     */
    public void notifyIndexPathChanged(IndexPath indexPath) {
        int pos = 0;
        for (int i = 0; i < indexPath.getSection(); i++) {
            pos += getTotalItemCountInSection(i);
        }
        // if section has header
        if (isSectionHasHeader(indexPath.getSection())) {
            pos += 1;
        }
        pos += indexPath.getRow();
        this.notifyItemChanged(pos);
    }

    public void notifySectionMoreData(int section, int itemCount) {
        int pos = 0;
        for (int i = 0; i < section; i++) {
            pos += getTotalItemCountInSection(i);
        }
        this.notifyItemRangeChanged(pos, itemCount);
    }

    //** Empty View ******************************************************************************//

    public void setEmptyView(int layoutResId, ViewGroup viewGroup) {
        View view = mLayoutInflater.inflate(layoutResId, viewGroup, false);
        setEmptyView(view);
    }

    public void setEmptyView(View emptyView) {
        if (mEmptyLayout == null) {
            mEmptyLayout = new FrameLayout(emptyView.getContext());
            final RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
            final ViewGroup.LayoutParams lp = emptyView.getLayoutParams();
            if (lp != null) {
                layoutParams.width = lp.width;
                layoutParams.height = lp.height;
            }
            mEmptyLayout.setLayoutParams(layoutParams);
        }
        mEmptyLayout.removeAllViews();
        mEmptyLayout.addView(emptyView);

        // Set emptyView click listener
        if (mEmptyViewClickListener != null) {
            mEmptyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEmptyViewClickListener != null) mEmptyViewClickListener.onEmptyViewClick();
                }
            });
        }
    }

    public void setEmptyView(View emptyView, OnEmptyViewListener listener) {
        setEmptyViewListener(listener);
        setEmptyView(emptyView);
    }

    public void showEmptyView() {
        isShowEmptyView = true;
        notifyDataSetChanged();
    }

    public void hideEmptyView() {
        isShowEmptyView = false;
        notifyDataSetChanged();
    }

    public boolean isEmptyViewShown() {
        return isShowEmptyView;
    }

    public void setEmptyViewListener(OnEmptyViewListener listener) {
        this.mEmptyViewClickListener = listener;
    }

    public void removeEmptyViewListener() {
        this.mEmptyViewClickListener = null;
    }

    /**
     * if you want to use subclass of BaseViewHolder in the adapter,
     * you must override the method to create new ViewHolder.
     *
     * @param view view
     * @return new ViewHolder
     */
    @SuppressWarnings("unchecked")
    protected BaseViewHolder createBaseViewHolder(View view) {
        Class temp = getClass();
        Class z = null;
        while (z == null && null != temp) {
            z = getInstancedGenericKClass(temp);
            temp = temp.getSuperclass();
        }
        BaseViewHolder k;
        // 泛型擦除会导致z为null
        if (z == null) {
            k = new BaseViewHolder(view);
        } else {
            k = createGenericKInstance(z, view);
        }
        return k != null ? k : new BaseViewHolder(view);
    }

    private Class getInstancedGenericKClass(Class z) {
        Type type = z.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type temp : types) {
                if (temp instanceof Class) {
                    Class tempClass = (Class) temp;
                    if (BaseViewHolder.class.isAssignableFrom(tempClass)) {
                        return tempClass;
                    }
                } else if (temp instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) temp).getRawType();
                    if (rawType instanceof Class && BaseViewHolder.class.isAssignableFrom((Class<?>) rawType)) {
                        return (Class<?>) rawType;
                    }
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private BaseViewHolder createGenericKInstance(Class z, View view) {
        try {
            Constructor constructor;
            // inner and unstatic class
            if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
                constructor = z.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (BaseViewHolder) constructor.newInstance(this, view);
            } else {
                constructor = z.getDeclaredConstructor(View.class);
                constructor.setAccessible(true);
                return (BaseViewHolder) constructor.newInstance(view);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    //** LoadMore ********************************************************************************//

    public interface BaseAdapterLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(BaseAdapterLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
        mIsLoadMoreLoading = false;
    }

    public void setLoadMoreView(LoadMoreView loadMoreView) {
        this.mLoadMoreView = loadMoreView;
    }

    public void enableLoadMore(boolean enable) {

        if (mIsLoadMoreEnable == enable) return;
        mIsLoadMoreEnable = enable;

        if (mIsLoadMoreEnable) {
            if (mLoadMoreView == null) mLoadMoreView = new DefaultLoadMoreView();
            mLoadMoreView.setStatus(LoadMoreView.STATUS_DEFAULT);
            notifyItemInserted(getLoadMoreViewPosition());
        } else {
            notifyItemRemoved(getLoadMoreViewPosition());
        }
    }

    public int getLoadMoreViewPosition() {
        return getItemCount() - 1; // Load More View should always be the last item
    }

    private BaseViewHolder createLoadingViewHolder(ViewGroup parent) {
        View view = mLayoutInflater.inflate(mLoadMoreView.getLayoutId(), parent, false);
        BaseViewHolder holder = createBaseViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadMoreView.setStatus(LoadMoreView.STATUS_DEFAULT);
                notifyItemChanged(getLoadMoreViewPosition());
            }
        });
        return holder;
    }

    public void setPreLoadNumber(int preLoadNumber) {
        if (preLoadNumber > 1) {
            mPreLoadNumber = preLoadNumber;
        }
    }

    /**
     * Returns the enabled status for load more.
     *
     * @return True if load more is enabled, false otherwise.
     */
    public boolean isLoadingMoreLoading() {
        return mIsLoadMoreLoading;
    }

    private void autoLoadMore(int position) {

        if (!mIsLoadMoreEnable) return;

        if (position < getItemCount() - mPreLoadNumber) return;

        if (mLoadMoreView.getStatus() != LoadMoreView.STATUS_DEFAULT) return;

        mLoadMoreView.setStatus(LoadMoreView.STATUS_LOADING);

        if (!mIsLoadMoreLoading) {
            mIsLoadMoreLoading = true;
            mLoadMoreListener.onLoadMore();
        }
    }

    /**
     * Refresh end, no more data
     *
     * @param gone if true, remove load more view
     */
    public void loadMoreEnd(boolean gone) {
        if (!mIsLoadMoreEnable) return;
        mIsLoadMoreLoading = false;
        mLoadMoreView.setLoadMoreEndGone(gone);
        if (gone) {
            notifyItemRemoved(getLoadMoreViewPosition());
        } else {
            mLoadMoreView.setStatus(LoadMoreView.STATUS_END);
            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    /**
     * Refresh complete
     */
    public void loadMoreComplete() {
        if (!mIsLoadMoreEnable) return;
        mIsLoadMoreLoading = false;
        mLoadMoreView.setStatus(LoadMoreView.STATUS_DEFAULT);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    /**
     * Refresh failed
     */
    public void loadMoreFail() {
        if (!mIsLoadMoreEnable) return;
        mIsLoadMoreLoading = false;
        mLoadMoreView.setStatus(LoadMoreView.STATUS_FAIL);
        notifyItemChanged(getLoadMoreViewPosition());
    }
}
