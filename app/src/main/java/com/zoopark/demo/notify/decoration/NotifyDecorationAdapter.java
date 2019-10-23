package com.zoopark.demo.notify.decoration;

import android.content.Context;

import com.zoopark.demo.notify.decoration.bean.DecorationBean;
import com.zoopark.demo.notify.decoration.provider.DecorationFooterProvider;
import com.zoopark.demo.notify.decoration.provider.DecorationHeaderProvider;
import com.zoopark.demo.notify.decoration.provider.DecorationItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class NotifyDecorationAdapter extends BaseAdapter {

    private DecorationItemProvider mDecorationItemProvider;
    private DecorationHeaderProvider mDecorationHeaderProvider;
    private DecorationFooterProvider mDecorationFooterProvider;

    public NotifyDecorationAdapter(Context context) {
        super(context);

        mDecorationItemProvider = new DecorationItemProvider(context);
        mDecorationHeaderProvider = new DecorationHeaderProvider(context);
        mDecorationFooterProvider = new DecorationFooterProvider(context);
        mDecorationItemProvider.setHeader(mDecorationHeaderProvider);
        mDecorationItemProvider.setFooter(mDecorationFooterProvider);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mDecorationItemProvider);
    }

    public void setData(List<DecorationBean> data) {
        mDecorationItemProvider.setData(data);
        mDecorationHeaderProvider.setData("init Header");
        mDecorationFooterProvider.setData("init Footer");
        this.notifySectionChanged(0);
    }

    public void setNotifyHeaderFooterListener(DecorationItemProvider.onNotifyHeaderFooterListener listener) {
        mDecorationItemProvider.setNotifyHeaderFooterListener(listener);
    }

    public void notifyHeader() {
        mDecorationHeaderProvider.setData("Header has notified");
        this.notifyHeaderChanged(0);
    }

    public void notifyFooter() {
        mDecorationFooterProvider.setData("Footer has notified");
        this.notifyFooterChanged(0);
    }

}
