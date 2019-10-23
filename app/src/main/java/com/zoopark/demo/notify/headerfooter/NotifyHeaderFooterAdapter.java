package com.zoopark.demo.notify.headerfooter;

import android.content.Context;

import com.zoopark.demo.notify.headerfooter.bean.HeaderFooterBean;
import com.zoopark.demo.notify.headerfooter.provider.FooterProvider;
import com.zoopark.demo.notify.headerfooter.provider.HeaderProvider;
import com.zoopark.demo.notify.headerfooter.provider.ItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class NotifyHeaderFooterAdapter extends BaseAdapter {

    private ItemProvider mItemProvider;
    private HeaderProvider mHeaderProvider;
    private FooterProvider mFooterProvider;

    public NotifyHeaderFooterAdapter(Context context) {
        super(context);

        mItemProvider = new ItemProvider(context);
        mHeaderProvider = new HeaderProvider(context);
        mFooterProvider = new FooterProvider(context);
        mItemProvider.setHeader(mHeaderProvider);
        mItemProvider.setFooter(mFooterProvider);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mItemProvider);
    }

    public void setData(List<HeaderFooterBean> data) {
        mItemProvider.setData(data);
        mHeaderProvider.setData("init Header");
        mFooterProvider.setData("init Footer");
        this.notifySectionChanged(0);
    }

    public void setNotifyHeaderFooterListener(ItemProvider.onNotifyHeaderFooterListener listener) {
        mItemProvider.setNotifyHeaderFooterListener(listener);
    }

    public void notifyHeader() {
        mHeaderProvider.setData("Header has notified");
        this.notifyHeaderChanged(0);
    }

    public void notifyFooter() {
        mFooterProvider.setData("Footer has notified");
        this.notifyFooterChanged(0);
    }

}
