package com.zoopark.rv.empty;

import android.content.Context;

import com.zoopark.rv.BaseAdapter;
import com.zoopark.rv.RVAnimation.provider.WidthElementProvider;

import java.util.List;

public class EmptyViewAdapter extends BaseAdapter {

    private WidthElementProvider mWidthElementProvider;

    public EmptyViewAdapter(Context context) {
        super(context);
        mWidthElementProvider = new WidthElementProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(mWidthElementProvider);
    }

    public void setData(List<Integer> list) {
        mWidthElementProvider.setItemData(list);
    }
}
