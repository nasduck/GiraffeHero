package com.zoopark.rv.base.provider;

import android.content.Context;

import com.zoopark.rv.animation.enter.BaseInAnimation;

public abstract class BaseItemProvider<T> extends BaseProvider {

    public Context mContext;
    private BaseSectionHeaderFooter mHeader;
    private BaseSectionHeaderFooter mFooter;

    public BaseItemProvider(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setData(T newData) {};

    public void addData(T moreData) {};

    public void setHeader(BaseSectionHeaderFooter header) {
        this.mHeader = header;
    }

    public void setFooter(BaseSectionHeaderFooter footer) {
        this.mFooter = footer;
    }

    public boolean hasHeader() {
        return mHeader != null;
    }

    public boolean hasFooter() {
        return mFooter != null;
    }

    public BaseSectionHeaderFooter getHeader() {
        return this.mHeader;
    }

    public BaseSectionHeaderFooter getFooter() {
        return this.mFooter;
    }

    public BaseInAnimation getAnimator() {
        return null;
    }
}