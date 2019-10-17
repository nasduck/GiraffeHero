package com.zoopark.demo.complex;

import android.content.Context;

import com.zoopark.demo.complex.bean.ItemBean;
import com.zoopark.demo.complex.bean.NewsBean;
import com.zoopark.demo.complex.provider.ButtonGroupProvider;
import com.zoopark.demo.complex.provider.DivideProvider;
import com.zoopark.demo.complex.provider.BannerProvider;
import com.zoopark.demo.complex.provider.ProjectItemProvider;
import com.zoopark.demo.complex.provider.ProjectHeaderProvider;
import com.zoopark.demo.complex.provider.news.NewsFooterProvider;
import com.zoopark.demo.complex.provider.news.NewsHeaderProvider;
import com.zoopark.demo.complex.provider.news.NewsItemProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class ComplexAdapter extends BaseAdapter {

    private BannerProvider mImageProvider;
    private ButtonGroupProvider mButtonGroupProvider;

    private NewsItemProvider mNewsItem;

    private ProjectHeaderProvider mProjectHeaderProvider;
    private ProjectItemProvider mProjectItemProvider;

    private DivideProvider mDivideProvider;

    public ComplexAdapter(Context context) {
        super(context);

        mImageProvider = new BannerProvider(context);
        mButtonGroupProvider = new ButtonGroupProvider(context);

        // News
        mNewsItem = new NewsItemProvider(context);
        mNewsItem.setHeader(new NewsHeaderProvider(context));
        mNewsItem.setFooter(new NewsFooterProvider(context));

        mProjectHeaderProvider = new ProjectHeaderProvider(context, "已开发的组件库");
        mProjectItemProvider = new ProjectItemProvider(context);
        mProjectItemProvider.setHeader(mProjectHeaderProvider);

        mDivideProvider = new DivideProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(
                mImageProvider,
                mButtonGroupProvider,
                mNewsItem,
                mDivideProvider,
                mProjectItemProvider
        );
    }

    public void setOneItemData(List<ItemBean> list) {
        mProjectItemProvider.setData(list);
    }

    /**
     * Set news data
     *
     * @param newsData New list of news
     */
    public void setNewsData(List<NewsBean> newsData) {
        mNewsItem.setData(newsData);
    }

    public void setAddClickListener(ProjectHeaderProvider.OnAddClickListener listener) {
        mProjectHeaderProvider.setAddClickListener(listener);
    }
}
