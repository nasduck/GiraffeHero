package com.zoopark.demo.animation.enter.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.animation.enter.BaseInAnimation;
import com.zoopark.rv.animation.enter.SlideInAnimation;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

public class SlideAnimItem extends BaseItemProvider {

    private SlideDirection mSlideDirection;

    public SlideAnimItem(Context context) {
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
        BaseInAnimation animation = new SlideInAnimation(mSlideDirection);
        return animation;
    }

    public void setSlideDirection(SlideDirection direction) {
        mSlideDirection = direction;
    }
}
