package com.zoopark.demo.notify.section;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zoopark.demo.R;
import com.zoopark.demo.notify.section.bean.SectionBean;
import com.zoopark.demo.notify.section.provider.SectionHeaderProvider;

import java.util.ArrayList;
import java.util.List;

public class NotifySectionActivity extends AppCompatActivity implements SectionHeaderProvider.onNotifyListener {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    private NotifySectionAdapter mAdapter;

    private List<SectionBean> mFirstSectionList;
    private List<SectionBean> mSecondSectionList;
    private List<SectionBean> mThirdSectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_section);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        mAdapter = new NotifySectionAdapter(this);
        mAdapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

        callGetData();
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

    public void callGetData() {
        mFirstSectionList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mFirstSectionList.add(new SectionBean("title", "content"));
        }
        mAdapter.setFirstData(mFirstSectionList);

        mSecondSectionList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mSecondSectionList.add(new SectionBean("title", "content"));
        }
        mAdapter.setSecondData(mSecondSectionList);

        mThirdSectionList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mThirdSectionList.add(new SectionBean("title", "content"));
        }
        mAdapter.setThirdData(mThirdSectionList);
    }

    @Override
    public void onNotify(int section) {
        switch (section) {
            case 0:
                mFirstSectionList.clear();
                mFirstSectionList = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    mFirstSectionList.add(new SectionBean("notify title", "notify content"));
                }
                mAdapter.setFirstData(mFirstSectionList);
                break;
            case 1:
                mSecondSectionList.clear();
                mSecondSectionList = new ArrayList<>();
                mAdapter.setSecondData(mSecondSectionList);
                break;
            case 2:
                for (int i = 0; i < 4; i++) {
                    mThirdSectionList.add(new SectionBean("insert title", "insert content"));
                }
                mAdapter.insertThirdData(mThirdSectionList);
                break;
        }
    }
}
