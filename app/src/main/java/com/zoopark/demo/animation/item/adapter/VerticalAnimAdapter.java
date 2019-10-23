package com.zoopark.demo.animation.item.adapter;

import android.content.Context;

import com.zoopark.demo.animation.item.provider.VerticalItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class VerticalAnimAdapter extends BaseAdapter {

    private VerticalItemProvider mItemProvider;

    public VerticalAnimAdapter(Context context) {
        super(context);

        mItemProvider = new VerticalItemProvider(context);

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
