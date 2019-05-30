package com.zoopark.rv;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

public abstract class LoadMoreView {

    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;

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
                holder.setVibility(getLoadingViewId(), View.VISIBLE);
                holder.setVibility(getLoadFailViewId(), View.GONE);
                holder.setVibility(getLoadEndViewId(), View.GONE);
                break;
            case STATUS_FAIL:
                holder.setVibility(getLoadingViewId(), View.GONE);
                holder.setVibility(getLoadFailViewId(), View.VISIBLE);
                holder.setVibility(getLoadEndViewId(), View.GONE);
                break;
            case STATUS_END:
                holder.setVibility(getLoadingViewId(), View.GONE);
                holder.setVibility(getLoadFailViewId(), View.GONE);
                holder.setVibility(getLoadEndViewId(), View.VISIBLE);
                break;
            case STATUS_DEFAULT:
                holder.setVibility(getLoadingViewId(), View.GONE);
                holder.setVibility(getLoadFailViewId(), View.GONE);
                holder.setVibility(getLoadEndViewId(), View.GONE);
                break;
        }
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
     * load end view, you can return 0
     *
     * @return
     */
    protected abstract @IdRes
	int getLoadEndViewId();
}
