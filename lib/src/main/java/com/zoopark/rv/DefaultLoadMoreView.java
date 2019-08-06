package com.zoopark.rv;

import com.zoopark.giraffe.lib.R;

/**
 * Default Load More View
 */
public class DefaultLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.custom_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.layout_loading;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.layout_load_fail;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.layout_load_end;
    }
}
