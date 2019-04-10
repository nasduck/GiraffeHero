package com.nasduck.rvprovider.RVAnimation.provider;

import android.content.Context;

import com.nasduck.lib.BaseItemProvider;
import com.nasduck.lib.BaseViewHolder;
import com.nasduck.lib.IndexPath;
import com.nasduck.rvprovider.R;

public class OneElementProvider extends BaseItemProvider {

    public OneElementProvider(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getLayout() {
        return R.layout.item_element_one;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
