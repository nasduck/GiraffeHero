package com.zoopark.rv.RVAnimation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.rv.RVAnimation.adapter.RVAnimationHorizontalAdapter;
import com.zoopark.rv.RVAnimation.adapter.RVAnimationVerticalAdapter;
import com.zoopark.rv.animation.FadeItemAnimator;
import com.zoopark.rv.animation.FlipItemAnimator;
import com.zoopark.rv.animation.enums.Benchmark;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rvprovider.R;

import java.util.ArrayList;
import java.util.List;

public class RVAnimationFlipActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private RVAnimationVerticalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvaniamtion_flip);
        // 绑定控件
        mRecyclerView = findViewById(R.id.recycler_view);
        mToolbar = findViewById(R.id.toolbar);

        // 设置标题栏
        setSupportActionBar(mToolbar);

        // 添加数据源
        for (int i = 0; i < 20; i++) {
            mList.add(i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new RVAnimationVerticalAdapter(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter.setItemData(mList);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new FlipItemAnimator(Benchmark.CENTER));
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

    public void onAnimTypeClick(View view) {
        switch (view.getId()) {
            case R.id.btn_center:
                mRecyclerView.setItemAnimator(new FlipItemAnimator(Benchmark.CENTER));
                break;
            case R.id.btn_x:
                mRecyclerView.setItemAnimator(new FlipItemAnimator(Benchmark.X));
                break;
            case R.id.btn_y:
                mRecyclerView.setItemAnimator(new FlipItemAnimator(Benchmark.Y));
                break;
        }
    }

    public void onAddClick(View view) {
        mList.add(6, 20000);
        mAdapter.notifyItemInserted(6);
    }

    public void onRemove(View view) {
        mList.remove(6);
        mAdapter.notifyItemRemoved(6);
    }

    public void onMove(View view) {
        mAdapter.notifyItemMoved(4, 6);
    }

    public void onChange(View view) {
        mList.set(5, 10000);
        mAdapter.notifyItemChanged(5);
    }


}
