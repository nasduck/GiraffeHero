package com.zoopark.demo.animation.enter.adapter;

import android.content.Context;

import com.zoopark.demo.animation.enter.provider.AnimCommonItem;
import com.zoopark.demo.animation.enter.provider.AnimTitleItem;
import com.zoopark.rv.animation.enums.Benchmark;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.BaseAdapter;

public class EnterHybridAnimAdapter extends BaseAdapter {

    private AnimTitleItem mScaleTitleItem;
    private AnimTitleItem mSlideTitleItem;
    private AnimCommonItem mScaleAnimItem;
    private AnimCommonItem mSlideAnimItem;

    public EnterHybridAnimAdapter(Context context) {
        super(context);
        mScaleTitleItem = new AnimTitleItem(context, "Scale Animation");
        mSlideTitleItem = new AnimTitleItem(context, "Slide Animation");

        mScaleAnimItem = new AnimCommonItem(context);
        mScaleAnimItem.setAnimationType(1);
        mScaleAnimItem.setScaleType(Benchmark.CENTER);
        mSlideAnimItem = new AnimCommonItem(context);
        mSlideAnimItem.setAnimationType(2);
        mSlideAnimItem.setSlideDirection(SlideDirection.RIGHT);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mScaleTitleItem, mScaleAnimItem, mSlideTitleItem, mSlideAnimItem);
    }
}
