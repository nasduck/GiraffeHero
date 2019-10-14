package com.zoopark.demo.complex.provider;

import android.content.Context;

import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;
import com.zoopark.rvprovider.R;

public class ImageProvider extends BaseItemProvider {

    public ImageProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_image;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
