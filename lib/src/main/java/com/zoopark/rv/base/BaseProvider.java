package com.zoopark.rv.base;

import android.support.annotation.IdRes;

public abstract class BaseProvider {

    public abstract int getLayout();
    public abstract void onBind(BaseViewHolder holder, IndexPath indexPath);
    public abstract int getItemCount();

    public void onClick(BaseViewHolder holder, IndexPath indexPath, @IdRes int viewId) {};
}
