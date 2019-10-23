package com.zoopark.rv.base.provider;

import android.support.annotation.IdRes;

import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

public abstract class BaseProvider {

    public abstract int getLayout();
    public abstract void onBind(BaseViewHolder holder, IndexPath indexPath);
    public abstract int getItemCount();

    public void onClick(BaseViewHolder holder, IndexPath indexPath, @IdRes int viewId) {};
}
