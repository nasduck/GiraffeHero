package com.zoopark.rv.animation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.zoopark.rv.animation.enums.SlideDirection;

/**
 * TranslationItemAnimator
 */

public class TranslationItemAnimator extends BaseItemAnimator {

    private int mWidth = 0;
    private int mHeight = 0;
    private SlideDirection mDirection;

    public TranslationItemAnimator(SlideDirection style) {
        mDirection = style;
    }

    @Override
    protected boolean addAnimationInit(RecyclerView.ViewHolder holder) {
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
                if (mWidth == 0) mHeight = holder.itemView.getRootView().getHeight();
                holder.itemView.setTranslationY(-mHeight);
                break;
            case BOTTOM:
                if (mWidth == 0) mHeight = holder.itemView.getRootView().getHeight();
                holder.itemView.setTranslationY(mHeight);
                break;
        }
        return true;
    }

    @Override
    protected ViewPropertyAnimator addAnimation(ViewPropertyAnimator animation) {
        switch (mDirection) {
            case RIGHT:
            case LEFT:
                return animation.translationX(0);
            case TOP:
            case BOTTOM:
                return animation.translationY(0);
        }
        return animation;
    }

    @Override
    protected void animationEnd(View view) {
        view.setTranslationX(0);
        view.setTranslationY(0);
    }

    @Override
    protected ViewPropertyAnimator removeAnimation(ViewPropertyAnimator animation) {
        switch (mDirection) {
            case LEFT:
                return animation.translationX(-mWidth);
            case RIGHT:
                return animation.translationX(mWidth);
            case TOP:
                return animation.translationY(-mHeight);
            case BOTTOM:
                return animation.translationY(mHeight);
        }
        return animation;
    }


}