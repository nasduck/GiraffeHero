package com.zoopark.demo.complex.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

public class DivideProvider extends BaseItemProvider {

    public DivideProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_divide;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
