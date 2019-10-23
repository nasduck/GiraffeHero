package com.zoopark.rv.loadmore;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.zoopark.rv.base.holder.BaseViewHolder;

public abstract class LoadMoreView {

    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL    = 3;
    public static final int STATUS_END     = 4;

    private int mStatus = STATUS_DEFAULT;
    private boolean isLoadMoreEndGone = false;

    public void setStatus(int newStatus) {
        this.mStatus = newStatus;
    }

    public int getStatus() {
        return mStatus;
    }

    public void convert(BaseViewHolder holder) {
        switch (mStatus) {
            case STATUS_LOADING:
                setLoadingGone(holder, true);
                setLoadFailGone(holder, false);
                setLoadEndGone(holder, false);
                break;
            case STATUS_FAIL:
                setLoadingGone(holder, false);
                setLoadFailGone(holder, true);
                setLoadEndGone(holder, false);
                break;
            case STATUS_END:
                setLoadingGone(holder, false);
                setLoadFailGone(holder, false);
                setLoadEndGone(holder, true);
                break;
            case STATUS_DEFAULT:
                setLoadingGone(holder, false);
                setLoadFailGone(holder, false);
                setLoadEndGone(holder, false);
                break;
        }
    }

    private void setLoadingGone(BaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadingViewId(), visible);
    }

    private void setLoadFailGone(BaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadFailViewId(), visible);
    }

    private void setLoadEndGone(BaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadEndViewId(), visible);
    }

    public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
        this.isLoadMoreEndGone = loadMoreEndGone;
    }

    public final boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return isLoadMoreEndGone;
    }

    /**
     * load more layout
     *
     * @return
     */
    public abstract @LayoutRes
	int getLayoutId();

    /**
     * loading view
     *
     * @return
     */
    protected abstract @IdRes
	int getLoadingViewId();

    /**
     * load fail view
     *
     * @return
     */
    protected abstract @IdRes
	int getLoadFailViewId();

    /**
     * load end view, return 0 if not exist
     *
     * @return
     */
    protected abstract @IdRes
	int getLoadEndViewId();
}
