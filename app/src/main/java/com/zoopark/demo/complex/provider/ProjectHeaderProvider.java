package com.zoopark.demo.complex.provider;

import android.content.Context;

import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseSectionHeaderFooter;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;
import com.zoopark.rvprovider.R;

public class ProjectHeaderProvider extends BaseSectionHeaderFooter<String> {

    public interface OnAddClickListener {
        void onAddClick();
    }

    private String mTitle;

    private OnAddClickListener mListener;

    public ProjectHeaderProvider(Context context, String title) {
        super(context);
        this.mTitle = title;
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

    public void setAddClickListener(OnAddClickListener listener) {
        this.mListener = listener;
    }
}
