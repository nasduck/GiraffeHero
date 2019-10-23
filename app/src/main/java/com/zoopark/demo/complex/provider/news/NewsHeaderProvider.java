package com.zoopark.demo.complex.provider.news;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseSectionHeaderFooter;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

public class NewsHeaderProvider extends BaseSectionHeaderFooter {

    public interface NewsHeaderProviderListener {
        void onNewsHeaderAddOneClick();
        void onNewsHeaderAddTwoClick();
    };

    private NewsHeaderProviderListener mListener;

    public NewsHeaderProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_news_header;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setClick(R.id.btn_add_one, this, indexPath);
        holder.setClick(R.id.btn_add_two, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (viewId) {
            case R.id.btn_add_one:
                if (mListener != null) mListener.onNewsHeaderAddOneClick();
                break;
            case R.id.btn_add_two:
                if (mListener != null) mListener.onNewsHeaderAddTwoClick();
                break;
        }
    }

    public void setListener(NewsHeaderProviderListener listener) {
        this.mListener = listener;
    }

}
