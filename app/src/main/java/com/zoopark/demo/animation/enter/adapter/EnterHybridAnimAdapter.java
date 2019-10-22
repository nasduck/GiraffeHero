package com.zoopark.demo.animation.enter.adapter;

import android.content.Context;

import com.zoopark.demo.animation.enter.provider.ScaleAnimItem;
import com.zoopark.demo.animation.enter.provider.SlideAnimItem;
import com.zoopark.demo.animation.enter.provider.AnimTitleItem;
import com.zoopark.rv.animation.enums.Benchmark;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.BaseAdapter;

public class EnterHybridAnimAdapter extends BaseAdapter {

    private AnimTitleItem mScaleTitleItem;
    private AnimTitleItem mSlideTitleItem;
    private ScaleAnimItem mScaleAnimItem;
    private SlideAnimItem mSlideAnimItem;

    public EnterHybridAnimAdapter(Context context) {
        super(context);
        mScaleTitleItem = new AnimTitleItem(context, "Scale Animation");
        mSlideTitleItem = new AnimTitleItem(context, "Slide Animation");

        mScaleAnimItem = new ScaleAnimItem(context);
        mScaleAnimItem.setScaleType(Benchmark.CENTER);
        mSlideAnimItem = new SlideAnimItem(context);
        mSlideAnimItem.setSlideDirection(SlideDirection.RIGHT);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mScaleTitleItem, mScaleAnimItem, mSlideTitleItem, mSlideAnimItem);
    }
}
