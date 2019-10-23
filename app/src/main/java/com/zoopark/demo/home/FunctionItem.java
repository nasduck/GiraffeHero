package com.zoopark.demo.home;

import android.content.Context;
import android.content.Intent;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.item.activity.HomeAnimActivity;
import com.zoopark.demo.animation.enter.activity.EnterAnimHomeActivity;
import com.zoopark.demo.complex.ComplexPageActivity;
import com.zoopark.demo.empty.EmptyViewActivity;
import com.zoopark.demo.notify.headerfooter.NotifyHeaderFooterActivity;
import com.zoopark.demo.notify.section.NotifySectionActivity;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

import java.util.ArrayList;
import java.util.List;

public class FunctionItem extends BaseItemProvider<List<FunctionBean>> {

    private List<FunctionBean> mData;

    public FunctionItem(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_function;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setImageDrawable(R.id.iv_icon, mContext.getResources().getDrawable(mData.get(indexPath.getRow()).getImageId()));
        holder.setText(R.id.tv_text, mData.get(indexPath.getRow()).getTitle());
        holder.setClick(R.id.container, this, indexPath);
    }

    @Override
    public void setData(List<FunctionBean> newData) {
        super.setData(newData);
        this.mData = newData;
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (indexPath.getRow()) {
            case 0:
                mContext.startActivity(new Intent(mContext, ComplexPageActivity.class));
                break;
            case 1:
                mContext.startActivity(new Intent(mContext, EmptyViewActivity.class));
                break;
            case 2:
                mContext.startActivity(new Intent(mContext, NotifyHeaderFooterActivity.class));
                break;
            case 3:
                mContext.startActivity(new Intent(mContext, NotifySectionActivity.class));
                break;
            case 4:
                mContext.startActivity(new Intent(mContext, EnterAnimHomeActivity.class));
                break;
            case 5:
                mContext.startActivity(new Intent(mContext, HomeAnimActivity.class));
                break;
        }
    }
}
