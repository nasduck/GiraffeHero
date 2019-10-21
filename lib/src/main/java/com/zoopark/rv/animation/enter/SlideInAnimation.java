package com.zoopark.rv.animation.enter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.zoopark.rv.animation.enter.BaseAnimation;
import com.zoopark.rv.animation.enums.SlideDirection;

public class SlideInAnimation extends BaseAnimation {

    private SlideDirection mSlideDirection;

    public SlideInAnimation() {
        this.mSlideDirection = SlideDirection.RIGHT;
    }

    public SlideInAnimation(SlideDirection slideDirection) {
        this.mSlideDirection = slideDirection;
    }

    @Override
    public AnimatorSet getAnimators(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = null;
        switch (mSlideDirection) {
            case TOP:
                view.setTranslationY(-view.getRootView().getHeight());
                animator = ObjectAnimator.ofFloat(view, "translationY", -view.getRootView().getHeight(), 0);
                break;
            case BOTTOM:
                view.setTranslationY(view.getRootView().getHeight());
                animator = ObjectAnimator.ofFloat(view, "translationY", view.getRootView().getHeight(), 0);
                break;
            case LEFT:
                view.setTranslationX(-view.getRootView().getWidth());
                animator = ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0);
                break;
            case RIGHT:
                view.setTranslationX(view.getRootView().getWidth());
                animator = ObjectAnimator.ofFloat(view, "translationX", view.getRootView().getWidth(), 0);
                break;
        }
        animator.setStartDelay(getStartDelay());
        animator.setDuration(getDuration());
        animator.setInterpolator(getInterpolator());
        animatorSet.play(animator);
        return animatorSet;
    }

}
