package com.zoopark.demo.complex.provider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;
import com.zoopark.demo.complex.bean.ItemBean;
import com.zoopark.rvprovider.R;

import java.util.ArrayList;
import java.util.List;

public class OneItemProvider extends BaseItemProvider {

    private List<ItemBean> mList;

    public OneItemProvider(Context context) {
        super(context);
        mList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_one_item;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setClick(R.id.container, this, indexPath);
        holder.setImageResource(R.id.image, mList.get(indexPath.getRow()).getImageId());
        holder.setText(R.id.tv_content, mList.get(indexPath.getRow()).getContent());
    }

    public void setData(List<ItemBean> list) {
        this.mList = list;
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        Intent intent= new Intent();
        switch (indexPath.getRow()) {
            case 0:
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/nasduck/RafikiPermissions"));
                mContext.startActivity(intent);
                break;
            case 1:
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/nasduck/GiantPandaDialog"));
                mContext.startActivity(intent);
                break;
            case 2:
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/nasduck/LesserPandaToast"));
                mContext.startActivity(intent);
                break;
            default:
                Toast.makeText(mContext, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
