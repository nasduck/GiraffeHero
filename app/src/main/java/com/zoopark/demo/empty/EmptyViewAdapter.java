package com.zoopark.demo.empty;

import android.content.Context;

import com.zoopark.demo.animation.provider.VerticalItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class EmptyViewAdapter extends BaseAdapter {

    private VerticalItemProvider mWidthElementProvider;

    public EmptyViewAdapter(Context context) {
        super(context);
        mWidthElementProvider = new VerticalItemProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mWidthElementProvider);
    }

    public void setData(List<Integer> list) {
        mWidthElementProvider.setData(list);
    }
}
