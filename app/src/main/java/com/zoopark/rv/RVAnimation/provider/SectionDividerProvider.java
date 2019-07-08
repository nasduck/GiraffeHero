package com.zoopark.rv.RVAnimation.provider;

import android.content.Context;

import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rvprovider.R;

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
