package com.zoopark.demo.complex;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zoopark.demo.R;
import com.zoopark.demo.complex.bean.ProjectBean;
import com.zoopark.demo.complex.bean.NewsBean;
import com.zoopark.demo.complex.loadmore.CustomLoadMoreView;
import com.zoopark.demo.complex.provider.ButtonGroupProvider;
import com.zoopark.demo.complex.provider.news.NewsHeaderProvider;
import com.zoopark.demo.empty.EmptyViewActivity;
import com.zoopark.rv.animation.change.FadeItemAnimator;
import com.zoopark.rv.animation.change.ScaleItemAnimator;
import com.zoopark.rv.animation.enums.Benchmark;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.BaseAdapter;
import com.zoopark.rv.base.IndexPath;
import com.zoopark.rv.empty.OnEmptyViewListener;

import java.util.ArrayList;
import java.util.List;

public class ComplexPageActivity extends AppCompatActivity implements
        BaseAdapter.BaseAdapterLoadMoreListener,
        NewsHeaderProvider.NewsHeaderProviderListener,
        ButtonGroupProvider.OnButtonGroupClickListener {

    // Image
    private int[] imageArray = {R.drawable.image_rafiki_permissions, R.drawable.image_giant_panda, R.drawable.image_lesser_panda};

    // Content
    private int[] contentArray = {R.string.introduce_rafiki_permissions, R.string.introduce_giant_panda, R.string.introduce_lesser_panda};

    private List<NewsBean> mNewsList = new ArrayList<>();
    private List<ProjectBean> mProjectList = new ArrayList<>();

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private ComplexAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_page);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        // init adapter
        mAdapter = new ComplexAdapter(this);
        mAdapter.enableLoadMore(true);      // enable load more
        mAdapter.setLoadMoreListener(this); // add load more listener
        mAdapter.setLoadMoreView(new CustomLoadMoreView());

        mRecyclerView.setAdapter(mAdapter);
        // set item animation, but it's maybe good that using item animation in simple list.
        // mRecyclerView.setItemAnimator(new FadeItemAnimator(SlideDirection.RIGHT));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create empty view
        TextView emptyView = new TextView(this);
        emptyView.setText(getResources().getText(R.string.complex_empty_tip));
        emptyView.setGravity(Gravity.CENTER);

        // Set empty view
        mAdapter.setEmptyView(emptyView, new OnEmptyViewListener() {
            @Override
            public void onEmptyViewClick() {
                callApiGetNews();
                callApiGetProject();
                mAdapter.hideEmptyView();
            }
        });
        // or you can user your own layout
        //mAdapter.setEmptyView(R.layout.layout_empty_view, mRecyclerView);
        mAdapter.showEmptyView();
    }

    /**
     * Call api to get data of news from server
     */
    private void callApiGetNews() {
        // fake data. create 2 news.
        for (int i = 0; i < 2; i++) {
            mNewsList.add(new NewsBean("News " + i, "Welcome to use giraffe hero!"));
        }
        // set data to adapter
        mAdapter.setNewsData(mNewsList);
    }

    /**
     * Call api to get data of projects from server
     */
    private void callApiGetProject() {
        // fake data.
        for (int i = 0; i < imageArray.length; i++) {
            mProjectList.add(new ProjectBean(imageArray[i], contentArray[i]));
        }
        mAdapter.setProjectData(mProjectList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载监听事件
     *
     * 加载完成后必要要调用完成事件方法
     * loadMoreComplete()
     * loadMoreEnd(boolean gone)
     * loadMoreFail()
     */
    @Override
    public void onLoadMore() {
        // 延时模拟加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mProjectList.size() < 20) {
                    // 加载更多拉取三条数据
                    mProjectList.add(new ProjectBean(R.drawable.ic_developping, R.string.introduce_new_item));
                    mProjectList.add(new ProjectBean(R.drawable.ic_developping, R.string.introduce_new_item));
                    mProjectList.add(new ProjectBean(R.drawable.ic_developping, R.string.introduce_new_item));
                    mAdapter.setProjectData(mProjectList);
                    // 加载更多完成后调用此方法
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd(false);
                }
            }
        }, 500);
    }

    @Override
    public void onNotifyHeaderClick() {
        mAdapter.notifyHeader();
    }

    @Override
    public void onNotifyFooterClick() {
        mAdapter.notifyFooter();
    }

    @Override
    public void onAddClick() {
        if (mNewsList.size() == 0) {
            mNewsList.add(new NewsBean("News", "Welcome to use giraffe hero!"));
            mAdapter.notifySectionChanged(2);
        } else {
            mNewsList.add(new NewsBean("News", "Welcome to use giraffe hero!"));
            mAdapter.notifySectionMoreData(2, 1);
        }

    }

    @Override
    public void onRemoveClick() {
        mNewsList.clear();
        mAdapter.notifySectionChanged(2);
    }

    @Override
    public void onChangeClick() {
        if (mNewsList.size() > 0) {
            mNewsList.set(mNewsList.size() - 1, new NewsBean("New News", "Welcome to use giraffe hero!"));
            mAdapter.notifyIndexPathChanged(new IndexPath(2, mNewsList.size() - 1));
        } else {
            Toast.makeText(this, "当前News List为空", Toast.LENGTH_SHORT).show();
        }
    }
}
