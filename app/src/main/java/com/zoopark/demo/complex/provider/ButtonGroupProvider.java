package com.zoopark.demo.complex.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

public class ButtonGroupProvider extends BaseItemProvider {

    public interface OnButtonGroupClickListener {
        void onAddClick();
        void onRemoveClick();
        void onChangeClick();
    }

    private OnButtonGroupClickListener mListener;

    public ButtonGroupProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_button_group;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setClick(R.id.btn_add, this, indexPath);
        holder.setClick(R.id.btn_remove, this, indexPath);
        holder.setClick(R.id.btn_change, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (viewId) {
            case R.id.btn_add:
                if (mListener != null) mListener.onAddClick();
                break;
            case R.id.btn_remove:
                if (mListener != null) mListener.onRemoveClick();
                break;
            case R.id.btn_change:
                if (mListener != null) mListener.onChangeClick();
                break;
        }
    }

    public void setListener(OnButtonGroupClickListener listener) {
        this.mListener = listener;
    }
}
