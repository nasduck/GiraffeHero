package com.zoopark.rv.RVAnimation.adapter;

import android.content.Context;

import com.zoopark.rv.BaseAdapter;
import com.zoopark.rv.RVAnimation.provider.HeightElementProvider;
import com.zoopark.rv.RVAnimation.provider.WidthElementProvider;

import java.util.List;

public class RVAnimationHorizontalAdapter extends BaseAdapter {

    private HeightElementProvider mHeightElementProvider;

    public RVAnimationHorizontalAdapter(Context context) {
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
