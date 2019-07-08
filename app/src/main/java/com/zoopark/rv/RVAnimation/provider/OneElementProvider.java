package com.zoopark.rv.RVAnimation.provider;

import android.content.Context;
import android.widget.Button;

import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rvprovider.R;

import java.util.ArrayList;
import java.util.List;

public class OneElementProvider extends BaseItemProvider {

    private List<Integer> mData;

    public OneElementProvider(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_element_one;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        Button view = holder.getView(R.id.button);
        view.setText(String.valueOf(mData.get(indexPath.getRow())));
    }

    public void setData(List<Integer> data) {
        mData = data;
    }
}
