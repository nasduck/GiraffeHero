package com.zoopark.demo.animation.adapter;

import android.content.Context;

import com.zoopark.demo.animation.provider.VerticalItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class VerticalAnimAdapter extends BaseAdapter {

    private VerticalItemProvider mWidthElementProvider;

    public VerticalAnimAdapter(Context context) {
        super(context);

        mWidthElementProvider = new VerticalItemProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mWidthElementProvider);
    }

    public void setItemData(List<Integer> list) {
        mWidthElementProvider.setData(list);
        this.notifySectionChanged(0);
    }
}
