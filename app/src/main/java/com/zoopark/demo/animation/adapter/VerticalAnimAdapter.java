package com.zoopark.demo.animation.adapter;

import android.content.Context;

import com.zoopark.demo.animation.provider.WidthElementProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class VerticalAnimAdapter extends BaseAdapter {

    private WidthElementProvider mWidthElementProvider;

    public VerticalAnimAdapter(Context context) {
        super(context);

        mWidthElementProvider = new WidthElementProvider(context);

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
