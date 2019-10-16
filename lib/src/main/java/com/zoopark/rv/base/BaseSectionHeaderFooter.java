package com.zoopark.rv.base;

import android.content.Context;

public abstract class BaseSectionHeaderFooter<T> extends BaseProvider {

    public Context mContext;

    public BaseSectionHeaderFooter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setData(T newData) {};
}