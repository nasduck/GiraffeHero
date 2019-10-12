package com.zoopark.rv.complex.provider;

import android.content.Context;
import android.widget.Toast;

import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rvprovider.R;

public class ButtonGroupProvider extends BaseItemProvider {

    public ButtonGroupProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_button_group;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setClick(R.id.btn_item_one, this, indexPath);
        holder.setClick(R.id.btn_item_two, this, indexPath);
        holder.setClick(R.id.btn_item_three, this, indexPath);
        holder.setClick(R.id.btn_item_four, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (viewId) {
            case R.id.btn_item_one:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_1_tip), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_item_two:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_2_tip), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_item_three:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_3_tip), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_item_four:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_4_tip), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
