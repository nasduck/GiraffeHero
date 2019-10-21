package com.zoopark.rv.animation.enter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.zoopark.rv.animation.enums.Benchmark;

public class ScaleInAnimation extends BaseAnimation {
    private static final float DEFAULT_SCALE_FROM = 0.5f;
    private final float mFrom;

    private Benchmark mBenchmark;

    public ScaleInAnimation() {
        this(DEFAULT_SCALE_FROM);
    }

    public ScaleInAnimation(float from) {
        this(Benchmark.CENTER, from);
    }

    public ScaleInAnimation(Benchmark benchmark, float from) {
        this.mBenchmark = benchmark;
        this.mFrom = from;
    }

    @Override
    public AnimatorSet getAnimators(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(getDuration());
        animatorSet.setStartDelay(getStartDelay());
        animatorSet.setInterpolator(getInterpolator());
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        switch (mBenchmark) {
            case X:
                scaleX = ObjectAnimator.ofFloat(view, "scaleX", mFrom, 1f);
                animatorSet.play(scaleX);
                break;
            case Y:
                scaleY = ObjectAnimator.ofFloat(view, "scaleY", mFrom, 1f);
                animatorSet.play(scaleY);
                break;
            case CENTER:
                scaleX = ObjectAnimator.ofFloat(view, "scaleX", mFrom, 1f);
                scaleY = ObjectAnimator.ofFloat(view, "scaleY", mFrom, 1f);
                animatorSet.play(scaleX).with(scaleY);
                break;
        }
        return animatorSet;
    }
}
