package com.zoopark.rv.complex.provider;

import android.content.Context;

import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rvprovider.R;

public class TwoItemsProvider extends BaseItemProvider {
    public TwoItemsProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_two_items;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
