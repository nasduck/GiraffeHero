package com.zoopark.demo.notify.section.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseSectionHeaderFooter;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

public class SectionHeaderProvider extends BaseSectionHeaderFooter {

    public interface onNotifyListener {
        void onNotify(int section);
    }

    private String mTitle;
    private String mButtonTitle;

    private onNotifyListener mListener;

    public SectionHeaderProvider(Context context, String title, String buttonTitle) {
        super(context);
        mTitle = title;
        mButtonTitle = buttonTitle;
    }

    @Override
    public int getLayout() {
        return R.layout.item_section_header;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_title, mTitle);
        holder.setText(R.id.btn_notify, mButtonTitle);
        holder.setClick(R.id.btn_notify, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        if (mListener != null) mListener.onNotify(indexPath.getSection());
    }

    public void setListener(onNotifyListener listener) {
        this.mListener = listener;
    }
}
