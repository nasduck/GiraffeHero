package com.zoopark.rv.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zoopark.rv.animation.enter.BaseInAnimation;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.provider.BaseProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;
import com.zoopark.rv.base.delegate.ProviderDelegate;
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
    private int preRowCount;
    private int mLastAnimPosition = -1;
    private BaseInAnimation mAnimation;
    private boolean isNotifyAnimation = false;

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
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.getItemViewType() != EMPTY_VIEW_TYPE && holder.getItemViewType() != LOAD_MORE_VIEW_TYPE) {
            if (mProviderDelegate.getAnimationCount() == 0) {
                mAnimation = null;
            } else if (mProviderDelegate.getAnimationCount() == 1 && getSectionNumber() == 1) {
                mAnimation = mProviderDelegate.getAnimation();
            } else {
                mAnimation = mProviderDelegate.getSection(getSection(holder.getAdapterPosition())).getAnimator();
            }

            if (mAnimation != null) {
                if (mLastAnimPosition < holder.getLayoutPosition()) {
                    mAnimation.getAnimators(holder.itemView).start();
                    mLastAnimPosition = holder.getLayoutPosition();
                    mAnimation = null;
                }
            }
        }
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
            int count = getTotalItemCount();
            this.preRowCount = getTotalRowCount();
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
        if (provider.hasHeader()) count += provider.getHeader().getItemCount();
        if (provider.hasFooter()) count += provider.getFooter().getItemCount();
        return count;
    };

    /**
     * 获取除某一 Section 之外的 Item 的数量 (不包含 Header 和 Footer)
     *
     * @param section
     * @return
     */
    public int getExtraRowCountInSection(int section) {
        int count = 0;
        for (int i = 0; i < getSectionNumber(); i++) {
            if (i != section) {
                count += getRowCountInSection(i);
            }
        }
        return count;
    }

    /**
     * Whether specified section has header
     *
     * @param section section
     * @return true if has header
     */
    public boolean isSectionHasHeader(int section) {
        return mProviderDelegate.getSection(section).hasHeader();
    }

    /**
     * Whether specified section has footer
     *
     * @param section section
     * @return true if has footer
     */
    public boolean isSectionHasFooter(int section) {
        return mProviderDelegate.getSection(section).hasFooter();
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

                // if first one
                if (position - lastItemCount == 0) {

                    // if first one is header
                    if (section.hasHeader()) {
                        indexPath.setRow(-1);
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section.getHeader()));
                    } else {
                        indexPath.setRow(position - lastItemCount);
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section));
                    }
                }
                // if last one
                else if (position + 1 - lastItemCount == sectionTotalItemCount) {
                    // if last one is footer
                    if (section.hasFooter()) {
                        indexPath.setRow(-2);
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section.getFooter()));
                    } else {
                        if (section.hasHeader()) {
                            indexPath.setRow(position - lastItemCount - 1);
                        } else {
                            indexPath.setRow(position - lastItemCount);
                        }
                        indexPath.setProviderSection(mProviderDelegate.indexOfProvider(section));
                    }
                } else {
                    if (section.hasHeader()) {
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
     * if notify animation when notify section
     *
     * @param isNotifyAnimation
     */
    public void setNotifyAnimation(boolean isNotifyAnimation) {
        this.isNotifyAnimation = isNotifyAnimation;
    }

    /**
     * Notify specified section
     *
     * @param section section
     */
    public void notifySectionChanged(int section) {
        if (isNotifyAnimation) {
            mLastAnimPosition = -1;
        }

        int pos = 0;
        for (int i = 0; i < section; i++) {
            pos += getTotalItemCountInSection(i);
        }

        // section item 变少
        int sectionPreRowCount = this.preRowCount - getExtraRowCountInSection(section);
        BaseItemProvider provider = mProviderDelegate.getSection(section);
        int sectionRowCount = getRowCountInSection(section);
        if (sectionRowCount < sectionPreRowCount) {
            // section 数量变到0，需要隐藏header和footer
            if (sectionRowCount == 0) {
                if (provider.hasHeader()) provider.getHeader().setItemCount(0);
                if (provider.hasFooter()) provider.getFooter().setItemCount(0);
                this.notifyItemRangeRemoved(pos, sectionPreRowCount + getSectionHeaderFooterCount(section));
            } else {
                if (provider.hasHeader()) pos++;
                this.notifyItemRangeRemoved(pos, sectionPreRowCount);
            }
        }
        // section item 数量不变或者变多
        else {
            if (sectionPreRowCount == 0) {
                if (provider.hasHeader()) provider.getHeader().setItemCount(1);
                if (provider.hasFooter()) provider.getFooter().setItemCount(1);
                this.notifyItemRangeChanged(pos, getRowCountInSection(section) + getSectionHeaderFooterCount(section));
            } else {
                if (provider.hasHeader()) pos++;
                this.notifyItemRangeChanged(pos, sectionPreRowCount);
            }
        }
    }

    /**
     * Notify specified row
     *
     * @param indexPath item location
     */
    public void notifyIndexPathChanged(IndexPath indexPath) {
        int pos = getPosition(indexPath);
        this.notifyItemChanged(pos);
    }

    /**
     * Notify more data in specified section
     *
     * @param section   section
     * @param itemCount the amount of data
     */
    public void notifySectionMoreData(int section, int itemCount) {
        int pos = 0;
        for (int i = 0; i < section; i++) {
            pos += getTotalItemCountInSection(i);
        }
        if (isSectionHasHeader(section)) pos++;
        pos += getRowCountInSection(section);
        this.notifyItemRangeInserted(pos - itemCount, itemCount);
    }

    /**
     * Notify new data that the item reflected at the bottom of section has been newly inserted.
     *
     * @param section   section
     */
    public void notifyIndexPathInserted(int section) {
        notifyIndexPathInserted(section, getRowCountInSection(section) - 1);
    }

    /**
     * Notify new data that the item reflected at the specified row of section has been newly inserted.
     *
     * @param section   section
     * @param row       the position in section
     */
    public void notifyIndexPathInserted(int section, int row) {
        int pos = getPosition(section, row);
        this.notifyItemInserted(pos);
    }

    /**
     * Notify new data that the item reflected at indexPath has been newly inserted.
     *
     * @param indexPath item location
     */
    public void notifyIndexPathInserted(IndexPath indexPath) {
        notifyIndexPathInserted(indexPath.getSection(), indexPath.getRow());
    }

    /**
     * Notify specified section's header
     *
     * @param section section
     */
    public void notifyHeaderChanged(int section) {
        if (isSectionHasHeader(section)) {
            this.notifyItemChanged(getPosition(section, 0) - 1);
        } else {
            throw new RuntimeException("This section does not have a header");
        }
    }

    /**
     * Notify specified section's footer
     *
     * @param section section
     */
    public void notifyFooterChanged(int section) {
        if (isSectionHasFooter(section)) {
            this.notifyItemChanged(getPosition(section, getRowCountInSection(section)));
        } else {
            throw new RuntimeException("This section already has a footer");
        }
    }

    public int getPosition(int section, int row) {
        int pos = 0;
        for (int i = 0; i < section; i++) {
            pos += getTotalItemCountInSection(i);
        }
        // if section has header
        if (isSectionHasHeader(section)) {
            pos += 1;
        }
        pos += row;
        return pos;
    }

    public int getPosition(IndexPath indexPath) {
        return getPosition(indexPath.getSection(), indexPath.getRow());
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

        if (isShowEmptyView) return;
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
