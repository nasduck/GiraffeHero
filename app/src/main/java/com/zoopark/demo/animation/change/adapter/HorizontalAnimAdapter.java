package com.zoopark.demo.animation.change.adapter;

import android.content.Context;

import com.zoopark.demo.animation.change.provider.HorizontalItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class HorizontalAnimAdapter extends BaseAdapter {

    private HorizontalItemProvider mItemProvider;

    public HorizontalAnimAdapter(Context context) {
        super(context);

        mItemProvider = new HorizontalItemProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mItemProvider);
    }

    public void setItemData(List<Integer> list) {
        mItemProvider.setData(list);
        this.notifySectionChanged(0);
    }
}
