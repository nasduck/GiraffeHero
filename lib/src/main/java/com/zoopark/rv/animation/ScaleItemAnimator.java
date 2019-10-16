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
    protected ViewPropertyAnimator addAnimation(ViewPropertyAnimator animator) {
        switch (mMark) {
            case Y:
                return animator.scaleY(1f);
            case X:
                return animator.scaleX(1f);
            case CENTER:
                return animator.scaleX(1f).scaleY(1f);
        }
        return animator;
    }

    @Override
    protected void animationEnd(View view) {
        view.setScaleX(1f);
        view.setScaleY(1f);
    }

    @Override
    protected ViewPropertyAnimator removeAnimation(ViewPropertyAnimator animator) {
        switch (mMark) {
            case Y:
                return animator.scaleY(0f);
            case X:
                return animator.scaleX(0f);
            case CENTER:
                return animator.scaleX(0f).scaleY(0f);
        }
        return animator;
    }

    @Override
    protected boolean changeExitAnimationInit(RecyclerView.ViewHolder holder) {
        holder.itemView.setScaleX(1f);
        holder.itemView.setScaleY(1f);
        return true;
    }

    @Override
    protected boolean changeEnterAnimationInit(RecyclerView.ViewHolder holder) {
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
    protected ViewPropertyAnimator changeExitAnimation(ViewPropertyAnimator animator) {
        switch (mMark) {
            case Y:
                return animator.scaleY(0f);
            case X:
                return animator.scaleX(0f);
            case CENTER:
                return animator.scaleX(0f).scaleY(0f);
        }
        return animator;
    }

    @Override
    protected ViewPropertyAnimator changeEnterAnimation(ViewPropertyAnimator animator) {
        switch (mMark) {
            case Y:
                return animator.scaleY(1f);
            case X:
                return animator.scaleX(1f);
            case CENTER:
                return animator.scaleX(1f).scaleY(1f);
        }
        return animator;
    }
}
