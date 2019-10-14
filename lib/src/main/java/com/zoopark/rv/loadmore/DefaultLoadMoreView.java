package com.zoopark.rv.loadmore;

import com.zoopark.giraffe.lib.R;

/**
 * Default Load More View
 */
public class DefaultLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.layout_load_more_custom;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.layout_load_more_loading;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.layout_load_more_fail;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.layout_load_more_end;
    }
}
