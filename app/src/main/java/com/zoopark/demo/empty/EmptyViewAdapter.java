package com.zoopark.demo.empty;

import android.content.Context;

import com.zoopark.rv.base.BaseAdapter;

public class EmptyViewAdapter extends BaseAdapter {

    private EmptyItemProvider mItemProvider;

    public EmptyViewAdapter(Context context) {
        super(context);
        mItemProvider = new EmptyItemProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mItemProvider);
    }
}
