package com.zoopark.rv.complex.provider;

import android.content.Context;

import com.zoopark.rvprovider.R;
import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;

/**
 * Section Divider
 */
public class SectionDividerProvider extends BaseItemProvider<Object, BaseViewHolder> {

    public SectionDividerProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_common_section_divider;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {}
}

