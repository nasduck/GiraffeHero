package com.zoopark.demo.animation.adapter;

import android.content.Context;

import com.zoopark.demo.animation.provider.WidthElementProvider;
import com.zoopark.rv.BaseAdapter;

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
        mProviderDelegate.registerProvider(mWidthElementProvider);
    }

    public void setItemData(List<Integer> list) {
        mWidthElementProvider.setItemData(list);
        this.notifySectionChanged(0);
    }
}
