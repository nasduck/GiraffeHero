package com.zoopark.demo.animation.enter.adapter;

import android.content.Context;

import com.zoopark.demo.animation.enter.provider.SlideAnimItem;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.BaseAdapter;

public class EnterAnimSlideAdapter extends BaseAdapter {

    private SlideAnimItem mAnimItem;

    public EnterAnimSlideAdapter(Context context, SlideDirection direction) {
        super(context);
        mAnimItem = new SlideAnimItem(context);
        mAnimItem.setSlideDirection(direction);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mAnimItem);
    }
}
