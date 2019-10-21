package com.zoopark.demo.animation.change.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

import java.util.ArrayList;
import java.util.List;

public class HorizontalItemProvider extends BaseItemProvider<List<Integer>> {

    private List<Integer> mData;

    public HorizontalItemProvider(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_element_height;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.button, String.valueOf(mData.get(indexPath.getRow())));
    }

    @Override
    public void setData(List<Integer> newData) {
        this.mData = newData;
    }
}
