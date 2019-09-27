package com.zoopark.rv.complex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.zoopark.rv.complex.bean.ItemBean;
import com.zoopark.rv.complex.provider.TitleProvider;
import com.zoopark.rvprovider.R;

import java.util.ArrayList;
import java.util.List;

public class ComplexPageActivity extends AppCompatActivity {

    // 图片的数组
    private int[] imageArray = {R.drawable.image_rafiki_permissions, R.drawable.image_giant_panda, R.drawable.image_lesser_panda};
    // 内容的介绍
    private int[] contentArray = {R.string.introduce_rafiki_permissions, R.string.introduce_giant_panda, R.string.introduce_lesser_panda};

    private List<ItemBean> mList = new ArrayList<>();

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private ComplexAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_page);
        // 绑定控件
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        // 设置标题栏
        setSupportActionBar(mToolbar);

        for (int i = 0; i < imageArray.length; i++) {
            mList.add(new ItemBean(imageArray[i], contentArray[i]));
        }

        mAdapter = new ComplexAdapter(this);
        mAdapter.setOneItemData(mList);
        mAdapter.setAddClickListener(new TitleProvider.OnAddClickListener() {
            @Override
            public void onClick() {
                mList.add(new ItemBean(R.drawable.ic_developping, R.string.introduce_new_item));
                mAdapter.setOneItemData(mList);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

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
}
