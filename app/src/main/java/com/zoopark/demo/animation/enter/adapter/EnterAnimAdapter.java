package com.zoopark.demo.animation.enter.adapter;

import android.content.Context;

import com.zoopark.demo.animation.enter.provider.AnimCommonItem;
import com.zoopark.rv.animation.enums.Benchmark;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.BaseAdapter;

public class EnterAnimAdapter extends BaseAdapter {


    private AnimCommonItem mAnimItem;

    public EnterAnimAdapter(Context context) {
        super(context);

        mAnimItem = new AnimCommonItem(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mAnimItem);
    }

    public void setAnimationType(int type) {
        mAnimItem.setAnimationType(type);
    }

    public void setScaleType(Benchmark benchmark) {
        mAnimItem.setScaleType(benchmark);
    }

    public void setSlideDirection(SlideDirection direction) {
        mAnimItem.setSlideDirection(direction);
    }
}
