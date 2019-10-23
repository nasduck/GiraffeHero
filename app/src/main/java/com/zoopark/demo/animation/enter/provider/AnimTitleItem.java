package com.zoopark.demo.animation.enter.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

public class AnimTitleItem extends BaseItemProvider {

    private String mTitle;

    public AnimTitleItem(Context context, String title) {
        super(context);
        this.mTitle = title;
    }

    @Override
    public int getLayout() {
        return R.layout.item_anim_title;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_title, mTitle);
    }
}
