package com.zoopark.demo.empty;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

public class EmptyItemProvider extends BaseItemProvider {

    public EmptyItemProvider(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getLayout() {
        return R.layout.item_empty;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
