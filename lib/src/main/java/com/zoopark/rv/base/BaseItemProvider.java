package com.zoopark.rv.base;

import android.content.Context;
import android.support.annotation.IdRes;

public abstract class BaseItemProvider<T, V extends BaseViewHolder> {

    public Context mContext;

    public BaseItemProvider(Context context) {
        this.mContext = context;
    }

    public abstract int getLayout();

    public abstract void onBind(V holder, IndexPath indexPath);

    public int getItemCount() {
        return 1;
    }

    public void onClick(V holder, IndexPath indexPath, @IdRes int viewId) {};

    public void setData(T newData) {};

    public void addData(T moreData) {};
}

