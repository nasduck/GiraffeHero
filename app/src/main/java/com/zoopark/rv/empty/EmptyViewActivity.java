package com.zoopark.rv.empty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.zoopark.rvprovider.R;

import java.util.ArrayList;
import java.util.List;

public class EmptyViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private EmptyViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_view);
        // 绑定控件
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        // 设置标题栏
        setSupportActionBar(mToolbar);

        mAdapter = new EmptyViewAdapter(this);
        mAdapter.setData(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

        // 设置空视图页面
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_no_content);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mAdapter.setEmptyView(imageView);
        mAdapter.showEmptyView();
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

    /** Click *************************************************************************************/

    public void onAddClick(View view) {
        for (int i = 0; i < 20; i++) {
            mList.add(i);
        }
        mAdapter.hideEmptyView();
    }

    public void onClearClick(View view) {
        mList.clear();
        mAdapter.showEmptyView();
    }
}
