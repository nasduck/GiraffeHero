package com.zoopark.rv.animation.enter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public abstract class BaseAnimation {

    /**
     * set animator
     * @param view
     * @return
     */
    public abstract AnimatorSet getAnimators(View view);

    private long duration = 500;
    private long startDelay = 0;
    private TimeInterpolator interpolator = new LinearInterpolator();

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(long startDelay) {
        this.startDelay = startDelay;
    }

    public TimeInterpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }
}
