package com.zoopark.rv.animation.item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.zoopark.rv.animation.enums.SlideDirection;

public class FadeItemAnimator extends BaseItemAnimator {

    private int mWidth;
    private int mHeight;
    private SlideDirection mDirection;

    public FadeItemAnimator(SlideDirection direction) {
        mDirection = direction;
    }

    @Override
    protected boolean addAnimationInit(RecyclerView.ViewHolder holder) {
        holder.itemView.setAlpha(0f);
        switch (mDirection) {
            case RIGHT:
                if (mWidth == 0) mWidth = holder.itemView.getRootView().getWidth();
                holder.itemView.setTranslationX(mWidth);
                break;
            case LEFT:
                if (mWidth == 0) mWidth = holder.itemView.getRootView().getWidth();
                holder.itemView.setTranslationX(-mWidth);
                break;
            case TOP:
                if (mHeight == 0) mHeight = holder.itemView.getRootView().getHeight();
                holder.itemView.setTranslationY(-mHeight);
                break;
            case BOTTOM:
                if (mHeight == 0) mHeight = holder.itemView.getRootView().getHeight();
                holder.itemView.setTranslationY(mHeight);
                break;
        }
        return true;
    }

    @Override
    protected ViewPropertyAnimator addAnimation(ViewPropertyAnimator animator) {
        animator.alpha(1f);
        switch (mDirection) {
            case RIGHT:
            case LEFT:
                return animator.translationX(0);
            case TOP:
            case BOTTOM:
                return animator.translationY(0);
        }
        return animator;
    }

    @Override
    protected void animationEnd(View view) {
        view.setTranslationX(0);
        view.setTranslationY(0);
        view.setAlpha(1f);
    }

    @Override
    protected ViewPropertyAnimator removeAnimation(ViewPropertyAnimator animator) {
        animator.alpha(0f);
        switch (mDirection) {
            case LEFT:
                return animator.translationX(-mWidth);
            case RIGHT:
                return animator.translationX(mWidth);
            case TOP:
                return animator.translationY(-mHeight);
            case BOTTOM:
                return animator.translationY(mHeight);
        }
        return animator;
    }

    @Override
    protected boolean changeExitAnimationInit(RecyclerView.ViewHolder holder) {
        holder.itemView.setAlpha(1f);
        holder.itemView.setTranslationX(0);
        holder.itemView.setTranslationY(0);
        return true;
    }

    @Override
    protected boolean changeEnterAnimationInit(RecyclerView.ViewHolder holder) {
        holder.itemView.setAlpha(0f);
        switch (mDirection) {
            case RIGHT:
                if (mWidth == 0) mWidth = holder.itemView.getRootView().getWidth();
                holder.itemView.setTranslationX(mWidth);
                break;
            case LEFT:
                if (mWidth == 0) mWidth = holder.itemView.getRootView().getWidth();
                holder.itemView.setTranslationX(-mWidth);
                break;
            case TOP:
                if (mHeight == 0) mHeight = holder.itemView.getRootView().getHeight();
                holder.itemView.setTranslationY(-mHeight);
                break;
            case BOTTOM:
                if (mHeight == 0) mHeight = holder.itemView.getRootView().getHeight();
                holder.itemView.setTranslationY(mHeight);
                break;
        }
        return true;
    }

    @Override
    protected ViewPropertyAnimator changeExitAnimation(ViewPropertyAnimator animator) {
        animator.alpha(0f);
        switch (mDirection) {
            case LEFT:
                return animator.translationX(-mWidth);
            case RIGHT:
                return animator.translationX(mWidth);
            case TOP:
                return animator.translationY(-mHeight);
            case BOTTOM:
                return animator.translationY(mHeight);
        }
        return animator;
    }

    @Override
    protected ViewPropertyAnimator changeEnterAnimation(ViewPropertyAnimator animator) {
        animator.alpha(1f);
        switch (mDirection) {
            case RIGHT:
            case LEFT:
                return animator.translationX(0);
            case TOP:
            case BOTTOM:
                return animator.translationY(0);
        }
        return animator;
    }


}
