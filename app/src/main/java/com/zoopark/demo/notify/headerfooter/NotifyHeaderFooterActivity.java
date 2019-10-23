package com.zoopark.demo.notify.headerfooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zoopark.demo.R;
import com.zoopark.demo.notify.headerfooter.bean.HeaderFooterBean;
import com.zoopark.demo.notify.headerfooter.provider.ItemProvider;

import java.util.ArrayList;
import java.util.List;

public class NotifyHeaderFooterActivity extends AppCompatActivity implements ItemProvider.onNotifyHeaderFooterListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private NotifyHeaderFooterAdapter mAdapter;

    private List<HeaderFooterBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_decoration);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new NotifyHeaderFooterAdapter(this);
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

    /**
     * Call api to get data of news from server
     */
    private void callApiGetData() {
        mList = new ArrayList<>();
        mList.add(new HeaderFooterBean("notify header", "notify"));
        mList.add(new HeaderFooterBean("notify footer", "notify"));
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
