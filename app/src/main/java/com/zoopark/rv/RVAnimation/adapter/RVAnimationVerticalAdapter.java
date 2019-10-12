package com.zoopark.rv.RVAnimation.adapter;

import android.content.Context;

import com.zoopark.rv.BaseAdapter;
import com.zoopark.rv.RVAnimation.provider.WidthElementProvider;

import java.util.List;

public class RVAnimationVerticalAdapter extends BaseAdapter {

    private WidthElementProvider mWidthElementProvider;

    public RVAnimationVerticalAdapter(Context context) {
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
