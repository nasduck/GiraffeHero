package com.zoopark.demo.animation.enter.adapter;

import android.content.Context;

import com.zoopark.demo.animation.enter.provider.ScaleAnimItem;
import com.zoopark.rv.animation.enums.Benchmark;
import com.zoopark.rv.base.BaseAdapter;

public class EnterAnimScaleAdapter extends BaseAdapter {

    private ScaleAnimItem mAnimItem;

    public EnterAnimScaleAdapter(Context context, Benchmark benchmark) {
        super(context);
        mAnimItem = new ScaleAnimItem(context);
        mAnimItem.setScaleType(benchmark);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mAnimItem);
    }
}
