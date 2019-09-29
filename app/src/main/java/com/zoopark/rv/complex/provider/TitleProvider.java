package com.zoopark.rv.complex.provider;

import android.content.Context;

import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rvprovider.R;

public class TitleProvider extends BaseItemProvider {

    public interface OnAddClickListener {
        void onAddClick();
    }

    private String mTitle;

    private OnAddClickListener mListener;

    public TitleProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_title;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_title, mTitle);
        holder.setClick(R.id.btn_add, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        if (viewId == R.id.btn_add) {
            if (mListener != null) {
                mListener.onAddClick();
            }
        }
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setAddClickListener(OnAddClickListener listener) {
        this.mListener = listener;
    }
}
