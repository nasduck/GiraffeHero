package com.zoopark.demo.animation.adapter;

import android.content.Context;

import com.zoopark.demo.animation.provider.HeightElementProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class HorizontalAnimAdapter extends BaseAdapter {

    private HeightElementProvider mHeightElementProvider;

    public HorizontalAnimAdapter(Context context) {
        super(context);

        mHeightElementProvider = new HeightElementProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(mHeightElementProvider);
    }

    public void setItemData(List<Integer> list) {
        mHeightElementProvider.setItemData(list);
        this.notifySectionChanged(0);
    }
}
