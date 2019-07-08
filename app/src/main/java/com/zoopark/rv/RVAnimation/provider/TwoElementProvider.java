package com.zoopark.rv.RVAnimation.provider;

import android.content.Context;

import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rvprovider.R;

public class TwoElementProvider extends BaseItemProvider {

    public TwoElementProvider(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getLayout() {
        return R.layout.item_element_two;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
