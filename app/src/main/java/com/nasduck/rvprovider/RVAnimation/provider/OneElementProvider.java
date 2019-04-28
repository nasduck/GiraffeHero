package com.nasduck.rvprovider.RVAnimation.provider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.nasduck.lib.BaseItemProvider;
import com.nasduck.lib.BaseViewHolder;
import com.nasduck.lib.IndexPath;
import com.nasduck.rvprovider.R;

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
