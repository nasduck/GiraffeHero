package com.zoopark.demo.complex.provider.news;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.demo.complex.bean.NewsBean;
import com.zoopark.rv.base.provider.BaseItemProvider;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

import java.util.ArrayList;
import java.util.List;

public class NewsItemProvider extends BaseItemProvider<List<NewsBean>> {

    private List<NewsBean> mData;

    public NewsItemProvider(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_news_item;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        NewsBean news = mData.get(indexPath.getRow());
        holder.setText(R.id.tv_title, news.getTitle());
        holder.setText(R.id.tv_content, news.getContent());
    }

    @Override
    public void setData(List<NewsBean> newData) {
        this.mData = newData;
    }

    @Override
    public void addData(List<NewsBean> moreData) {
        this.mData.addAll(moreData);
    }
}
