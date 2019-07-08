package com.zoopark.rv.complex.provider;

import android.content.Context;
import android.widget.Toast;

import com.zoopark.rvprovider.R;
import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rv.complex.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NewsItemProvider extends BaseItemProvider<List<NewsBean>, BaseViewHolder> {

    private List<NewsBean> mData;

    public NewsItemProvider(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getLayout() {
        return R.layout.item_news_item;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        if (mData.size() != 0) {
            NewsBean news = mData.get(indexPath.getRow());
            holder.setText(R.id.tv_title, news.getTitle());
            holder.setText(R.id.tv_time, news.getGmt());
            holder.setText(R.id.tv_source, "来源：" + news.getSource());
        }
        holder.setClick(R.id.layout, this, indexPath);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void setData(List<NewsBean> newData) {
        this.mData = newData;
    }

    public void addData(List<NewsBean> extraData) {
        mData.addAll(extraData);
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        Toast.makeText(mContext, mData.get(indexPath.getRow()).getTitle(), Toast.LENGTH_LONG).show();
    }
}
