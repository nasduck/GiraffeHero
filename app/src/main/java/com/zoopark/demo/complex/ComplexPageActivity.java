package com.zoopark.demo.complex;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zoopark.demo.R;
import com.zoopark.demo.complex.bean.ItemBean;
import com.zoopark.demo.complex.bean.NewsBean;
import com.zoopark.demo.complex.provider.ProjectHeaderProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ComplexPageActivity extends AppCompatActivity implements
        ProjectHeaderProvider.OnAddClickListener, BaseAdapter.BaseAdapterLoadMoreListener {

    // Image
    private int[] imageArray = {R.drawable.image_rafiki_permissions, R.drawable.image_giant_panda, R.drawable.image_lesser_panda};

    // Content
    private int[] contentArray = {R.string.introduce_rafiki_permissions, R.string.introduce_giant_panda, R.string.introduce_lesser_panda};

    private List<ItemBean> mList = new ArrayList<>();

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

        // fake data
        for (int i = 0; i < imageArray.length; i++) {
            mList.add(new ItemBean(imageArray[i], contentArray[i]));
        }

        // init adapter
        mAdapter = new ComplexAdapter(this);
        mAdapter.setOneItemData(mList);     // 设置数据
        mAdapter.enableLoadMore(true);      // enable load more
        mAdapter.setAddClickListener(this); // 添加增加item的监听事件
        mAdapter.setLoadMoreListener(this); // 添加加载更多监听事件

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        callApiGetNews();
    }

    /**
     * Call api to get data of news from server
     */
    private void callApiGetNews() {
        // fake data. create 3 news.
        List<NewsBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new NewsBean("News " + i, "Welcome to use giraffe hero!"));
        }
        // set data to adapter
        mAdapter.setNewsData(list);
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
     * 新增item
     */
    @Override
    public void onAddClick() {
        mList.add(new ItemBean(R.drawable.ic_developping, R.string.introduce_new_item));
        mAdapter.setOneItemData(mList);
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
                // 加载更多拉取三条数据
                mList.add(new ItemBean(R.drawable.ic_developping, R.string.introduce_new_item));
                mList.add(new ItemBean(R.drawable.ic_developping, R.string.introduce_new_item));
                mList.add(new ItemBean(R.drawable.ic_developping, R.string.introduce_new_item));
                mAdapter.setOneItemData(mList);
                // 加载更多完成后调用此方法
                mAdapter.loadMoreComplete();
            }
        }, 1000);
    }
}
