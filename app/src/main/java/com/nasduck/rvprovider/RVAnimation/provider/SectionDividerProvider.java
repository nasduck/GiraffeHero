package com.nasduck.rvprovider.RVAnimation.provider;

import android.content.Context;

import com.nasduck.lib.BaseItemProvider;
import com.nasduck.lib.BaseViewHolder;
import com.nasduck.lib.IndexPath;
import com.nasduck.rvprovider.R;

public class SectionDividerProvider extends BaseItemProvider {

    public SectionDividerProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_section_divider;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
