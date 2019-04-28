package com.nasduck.rvprovider.RVAnimation;

import android.content.Context;

import com.nasduck.lib.BaseAdapter;
import com.nasduck.rvprovider.RVAnimation.provider.OneElementProvider;

import java.util.List;

public class RVAnimationAdapter extends BaseAdapter {

    private OneElementProvider mOneElementProvider;

    public RVAnimationAdapter(Context context) {
        super(context);

        mOneElementProvider = new OneElementProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(mOneElementProvider);
    }

    public void setData(List<Integer> list) {
        mOneElementProvider.setData(list);
    }
}
