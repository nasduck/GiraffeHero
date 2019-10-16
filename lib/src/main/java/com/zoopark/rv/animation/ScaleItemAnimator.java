package com.zoopark.rv.animation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.zoopark.rv.animation.enums.Benchmark;

public class ScaleItemAnimator extends BaseItemAnimator {

    private Benchmark mMark;

    public ScaleItemAnimator(Benchmark benchmark) {
        mMark = benchmark;
    }

    @Override
    protected boolean addAnimationInit(RecyclerView.ViewHolder holder) {
        switch (mMark) {
            case Y:
                holder.itemView.setScaleY(0f);
                break;
            case X:
                holder.itemView.setScaleX(0f);
                break;
            case CENTER:
                holder.itemView.setScaleX(0f);
                holder.itemView.setScaleY(0f);
                break;
        }
        return true;
    }

    @Override
    protected ViewPropertyAnimator addAnimation(ViewPropertyAnimator animation) {
        switch (mMark) {
            case Y:
                return animation.scaleY(1f);
            case X:
                return animation.scaleX(1f);
            case CENTER:
                return animation.scaleX(1f).scaleY(1f);
        }
        return animation;
    }

    @Override
    protected void animationEnd(View view) {
        view.setScaleX(1f);
        view.setScaleY(1f);
    }

    @Override
    protected ViewPropertyAnimator removeAnimation(ViewPropertyAnimator animation) {
        switch (mMark) {
            case Y:
                return animation.scaleY(0f);
            case X:
                return animation.scaleX(0f);
            case CENTER:
                return animation.scaleX(0f).scaleY(0f);
        }
        return animation;
    }
}
