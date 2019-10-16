package com.zoopark.rv.animation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    protected ViewPropertyAnimator addAnimation(ViewPropertyAnimator animator) {
        switch (mMark) {
            case X:
                animator.rotationX(0);
                break;
            case Y:
                animator.rotationY(0).alpha(1f);
                break;
        }
        return animator;
    }

    @Override
    protected void animationEnd(View view) {
        view.setRotationX(0);
        view.setRotationY(0);
        view.setAlpha(1f);
    }

    @Override
    protected ViewPropertyAnimator removeAnimation(ViewPropertyAnimator animator) {
        switch (mMark) {
            case X:
                animator.rotationX(-90);
                break;
            case Y:
                animator.rotationY(-90).alpha(0f);
                break;
        }
        return animator;
    }

    @Override
    protected boolean changeExitAnimationInit(RecyclerView.ViewHolder holder) {
        holder.itemView.setRotationX(0);
        holder.itemView.setRotationY(0);
        holder.itemView.setAlpha(1f);
        return true;
    }

    @Override
    protected boolean changeEnterAnimationInit(RecyclerView.ViewHolder holder) {
        holder.itemView.setAlpha(0f);
        switch (mMark) {
            case X:
                holder.itemView.setRotationX(-180);
                break;
            case Y:
                holder.itemView.setRotationY(-180);
                break;
        }
        return true;
    }

    @Override
    protected ViewPropertyAnimator changeExitAnimation(ViewPropertyAnimator animator) {
        animator.alpha(0f);
        switch (mMark) {
            case X:
                animator.rotationX(-180);
                break;
            case Y:
                animator.rotationY(-180);
                break;
        }
        return animator;
    }

    @Override
    protected ViewPropertyAnimator changeEnterAnimation(ViewPropertyAnimator animator) {
        animator.alpha(1f);
        switch (mMark) {
            case X:
                animator.rotationX(-360);
                break;
            case Y:
                animator.rotationY(-360);
                break;
        }
        return animator;
    }
}
