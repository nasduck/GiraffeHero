package com.zoopark.demo.animation.enter.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.animation.enter.BaseAnimation;
import com.zoopark.rv.animation.enter.ScaleInAnimation;
import com.zoopark.rv.animation.enter.SlideInAnimation;
import com.zoopark.rv.animation.enums.Benchmark;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

public class ScaleAnimItem extends BaseItemProvider {

    private Benchmark mBenchmark;

    public ScaleAnimItem(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_anim_common;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }

    @Override
    public BaseAnimation getAnimator() {
        BaseAnimation animation = new ScaleInAnimation(mBenchmark);
        return animation;
    }

    public void setScaleType(Benchmark benchmark) {
        mBenchmark = benchmark;
    }
}
