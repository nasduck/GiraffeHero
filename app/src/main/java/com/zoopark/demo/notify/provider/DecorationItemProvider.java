package com.zoopark.demo.notify.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.demo.notify.bean.DecorationBean;
import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

import java.util.ArrayList;
import java.util.List;

public class DecorationItemProvider extends BaseItemProvider<List<DecorationBean>> {

    public interface onNotifyHeaderFooterListener {
        void onNotifyHeader();
        void onNotifyFooter();
    }

    private onNotifyHeaderFooterListener mListener;

    private List<DecorationBean> mData;

    public DecorationItemProvider(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_decoration_item;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_title, mData.get(indexPath.getRow()).getTitle());
        holder.setText(R.id.btn_notify, mData.get(indexPath.getRow()).getBtnTitle());
        holder.setClick(R.id.btn_notify, this, indexPath);
    }

    @Override
    public void setData(List<DecorationBean> newData) {
        super.setData(newData);
        mData = newData;
    }

    public void setNotifyHeaderFooterListener(onNotifyHeaderFooterListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (indexPath.getRow()) {
            case 0:
                if (mListener != null) mListener.onNotifyHeader();
                break;
            case 1:
                if (mListener != null) mListener.onNotifyFooter();
                break;
        }
    }
}
