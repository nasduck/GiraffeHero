package com.nasduck.rvprovider.RVAnimation;

import android.content.Context;

import com.nasduck.lib.BaseAdapter;
import com.nasduck.rvprovider.RVAnimation.provider.OneElementProvider;
import com.nasduck.rvprovider.RVAnimation.provider.SectionDividerProvider;
import com.nasduck.rvprovider.RVAnimation.provider.TwoElementProvider;

public class RVAnimationAdapter extends BaseAdapter {

    private OneElementProvider mOneElementProvider;
    private TwoElementProvider mTwoElementProvider;
    private SectionDividerProvider mSectionDividerProvider;

    public RVAnimationAdapter(Context context) {
        super(context);

        mOneElementProvider = new OneElementProvider(context);
        mTwoElementProvider = new TwoElementProvider(context);
        mSectionDividerProvider = new SectionDividerProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mOneElementProvider, mSectionDividerProvider,
                mTwoElementProvider, mSectionDividerProvider);
    }
}
