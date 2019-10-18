package com.zoopark.demo.notify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;
import com.zoopark.demo.notify.bean.DecorationBean;
import com.zoopark.demo.notify.provider.DecorationItemProvider;

import java.util.ArrayList;
import java.util.List;

public class NotifyDecorationActivity extends AppCompatActivity implements DecorationItemProvider.onNotifyHeaderFooterListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private NotifyDecorationAdapter mAdapter;

    private List<DecorationBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_decoration);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new NotifyDecorationAdapter(this);
        mAdapter.setNotifyHeaderFooterListener(this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        callApiGetData();
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

    public void onClearClick(View view) {
        mList.clear();
        mList = new ArrayList<>();
        mAdapter.setData(mList);
    }

    /**
     * Call api to get data of news from server
     */
    private void callApiGetData() {
        mList = new ArrayList<>();
        mList.add(new DecorationBean("notify header", "notify"));
        mList.add(new DecorationBean("notify footer", "notify"));
        mAdapter.setData(mList);
    }

    @Override
    public void onNotifyHeader() {
        mAdapter.notifyHeader();
    }


    @Override
    public void onNotifyFooter() {
        mAdapter.notifyFooter();
    }

}