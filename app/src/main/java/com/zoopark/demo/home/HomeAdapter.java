package com.zoopark.demo.home;

import android.content.Context;

import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class HomeAdapter extends BaseAdapter {

    private FunctionItem mFunctionItem;


    public HomeAdapter(Context context) {
        super(context);
        mFunctionItem = new FunctionItem(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mFunctionItem);
    }

    public void setData(List<FunctionBean> list) {
        mFunctionItem.setData(list);
        this.notifySectionChanged(0);
    }
}
