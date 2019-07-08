package com.zoopark.rv.complex.provider;

import android.content.Context;

import com.zoopark.rvprovider.R;
import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;

public class TitleProvider extends BaseItemProvider<Object, BaseViewHolder> {

    private String mTitle;

    public TitleProvider(Context context, String title) {
        super(context);

        this.mTitle = title;
    }

    @Override
    public int getLayout() {
        return R.layout.item_common_title;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_left_title, mTitle);
    }
}
