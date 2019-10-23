package com.zoopark.demo.complex.provider.news;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseSectionHeaderFooter;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

public class NewsHeaderProvider extends BaseSectionHeaderFooter<String> {

    public interface NewsHeaderProviderListener {
        void onNotifyHeaderClick();
        void onNotifyFooterClick();
    };

    private NewsHeaderProviderListener mListener;

    private String mTitle;

    public NewsHeaderProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_news_header;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_title, mTitle);
        holder.setClick(R.id.btn_add_one, this, indexPath);
        holder.setClick(R.id.btn_add_two, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (viewId) {
            case R.id.btn_add_one:
                if (mListener != null) mListener.onNotifyHeaderClick();
                break;
            case R.id.btn_add_two:
                if (mListener != null) mListener.onNotifyFooterClick();
                break;
        }
    }

    public void setListener(NewsHeaderProviderListener listener) {
        this.mListener = listener;
    }

    @Override
    public void setData(String newData) {
        this.mTitle = newData;
    }
}
