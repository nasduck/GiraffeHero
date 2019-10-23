package com.zoopark.demo.notify.section;

import android.content.Context;

import com.zoopark.demo.notify.section.bean.SectionBean;
import com.zoopark.demo.notify.section.provider.SectionFooterProvider;
import com.zoopark.demo.notify.section.provider.SectionHeaderProvider;
import com.zoopark.demo.notify.section.provider.SectionItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class NotifySectionAdapter extends BaseAdapter {

    private SectionItemProvider mFirstSectionItemProvider;
    private SectionItemProvider mSecondSectionItemProvider;
    private SectionItemProvider mThirdSectionItemProvider;

    private SectionHeaderProvider mFirstSectionHeaderProvider;
    private SectionFooterProvider mFirstSectionFooterProvider;

    private SectionHeaderProvider mSecondSectionHeaderProvider;
    private SectionFooterProvider mSecondSectionFooterProvider;

    private SectionHeaderProvider mThirdSectionHeaderProvider;
    private SectionFooterProvider mThirdSectionFooterProvider;

    public NotifySectionAdapter(Context context) {
        super(context);

        mFirstSectionItemProvider = new SectionItemProvider(context);
        mFirstSectionHeaderProvider = new SectionHeaderProvider(context, "First Section", "notify");
        mFirstSectionFooterProvider = new SectionFooterProvider(context);
        mFirstSectionItemProvider.setHeader(mFirstSectionHeaderProvider);
        mFirstSectionItemProvider.setFooter(mFirstSectionFooterProvider);

        mSecondSectionItemProvider = new SectionItemProvider(context);
        mSecondSectionHeaderProvider = new SectionHeaderProvider(context, "Second Section", "remove");
        mSecondSectionFooterProvider = new SectionFooterProvider(context);
        mSecondSectionItemProvider.setHeader(mSecondSectionHeaderProvider);
        mSecondSectionItemProvider.setFooter(mSecondSectionFooterProvider);

        mThirdSectionItemProvider = new SectionItemProvider(context);
        mThirdSectionHeaderProvider = new SectionHeaderProvider(context, "Third Section", "insert");
        mThirdSectionFooterProvider = new SectionFooterProvider(context);
        mThirdSectionItemProvider.setHeader(mThirdSectionHeaderProvider);
        mThirdSectionItemProvider.setFooter(mThirdSectionFooterProvider);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mFirstSectionItemProvider, mSecondSectionItemProvider, mThirdSectionItemProvider);
    }

    public void setFirstData(List<SectionBean> data) {
        mFirstSectionItemProvider.setData(data);
        this.notifySectionChanged(0);
    }

    public void setSecondData(List<SectionBean> data) {
        mSecondSectionItemProvider.setData(data);
        this.notifySectionChanged(1);
    }

    public void setThirdData(List<SectionBean> data) {
        mThirdSectionItemProvider.setData(data);
        this.notifySectionChanged(2);
    }

    public void insertThirdData(List<SectionBean> data) {
        mThirdSectionItemProvider.setData(data);
        this.notifyIndexPathInserted(2);
    }

    public void setListener(SectionHeaderProvider.onNotifyListener listener) {
        mFirstSectionHeaderProvider.setListener(listener);
        mSecondSectionHeaderProvider.setListener(listener);
        mThirdSectionHeaderProvider.setListener(listener);
    }
}
