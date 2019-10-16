package com.zoopark.rv.animation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.zoopark.rv.animation.enums.Benchmark;

public class FlipItemAnimator extends BaseItemAnimator {

    private Benchmark mMark;

    public FlipItemAnimator(Benchmark mark) {
        mMark = mark;
    }

    @Override
    protected boolean addAnimationInit(RecyclerView.ViewHolder holder) {
        switch (mMark) {
            case X:
                holder.itemView.setRotationX(-90);
                break;
            case Y:
                holder.itemView.setRotationY(-90);
                holder.itemView.setAlpha(0f);
                break;
        }
        return true;
    }

    @Override
    protected ViewPropertyAnimator addAnimation(ViewPropertyAnimator animation) {
        switch (mMark) {
            case X:
                animation.rotationX(0);
                break;
            case Y:
                animation.rotationY(0).alpha(1f);
                break;
        }
        return animation;
    }

    @Override
    protected void animationEnd(View view) {
        view.setRotationX(0);
        view.setRotationY(0);
        view.setAlpha(1f);
    }

    @Override
    protected ViewPropertyAnimator removeAnimation(ViewPropertyAnimator animation) {
        switch (mMark) {
            case X:
                animation.rotationX(-90);
                break;
            case Y:
                animation.rotationY(-90).alpha(0f);
                break;
        }
        return animation;
    }
}
