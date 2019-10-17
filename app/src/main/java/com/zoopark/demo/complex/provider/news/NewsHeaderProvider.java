package com.zoopark.demo.complex.provider.news;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseSectionHeaderFooter;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

public class NewsHeaderProvider extends BaseSectionHeaderFooter {

    public interface NewsHeaderProviderListener {
        void onNewsHeaderAddClick();
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
        holder.setClick(R.id.btn_add, this, indexPath);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        if (viewId == R.id.btn_add) {
            if (mListener != null) {
                mListener.onNewsHeaderAddClick();
            }
        }
    }

    public void setListener(NewsHeaderProviderListener listener) {
        this.mListener = listener;
    }

}
