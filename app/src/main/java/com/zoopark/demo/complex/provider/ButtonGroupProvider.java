package com.zoopark.demo.complex.provider;

import android.content.Context;
import android.widget.Toast;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

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
        holder.setClick(R.id.btn_one, this, indexPath);
        holder.setClick(R.id.btn_two, this, indexPath);
        holder.setClick(R.id.btn_three, this, indexPath);
        holder.setClick(R.id.btn_four, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (viewId) {
            case R.id.btn_one:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_1_tip), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_two:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_2_tip), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_three:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_3_tip), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_four:
                Toast.makeText(mContext, mContext.getResources().getText(R.string.button_group_4_tip), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
