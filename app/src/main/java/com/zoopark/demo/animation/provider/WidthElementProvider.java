package com.zoopark.demo.animation.provider;

import android.content.Context;
import android.widget.Button;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

import java.util.ArrayList;
import java.util.List;

public class WidthElementProvider extends BaseItemProvider {

    private List<Integer> mData;

    public WidthElementProvider(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_element_width;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        Button button = holder.getView(R.id.button);
        button.setText(String.valueOf(mData.get(indexPath.getRow())));
    }

    public void setItemData(List<Integer> data) {
        this.mData = data;
    }
}
