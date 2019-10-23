package com.zoopark.demo.animation.enter.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.animation.enter.BaseInAnimation;
import com.zoopark.rv.animation.enter.ScaleInAnimation;
import com.zoopark.rv.animation.enums.Benchmark;
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
        return R.layout.item_empty;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }

    @Override
    public BaseInAnimation getAnimator() {
        BaseInAnimation animation = new ScaleInAnimation(mBenchmark);
        return animation;
    }

    public void setScaleType(Benchmark benchmark) {
        mBenchmark = benchmark;
    }
}
