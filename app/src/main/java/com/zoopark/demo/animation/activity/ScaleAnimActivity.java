package com.zoopark.demo.animation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.adapter.VerticalAnimAdapter;
import com.zoopark.rv.animation.BaseItemAnimator;
import com.zoopark.rv.animation.ScaleItemAnimator;
import com.zoopark.rv.animation.enums.Benchmark;

import java.util.ArrayList;
import java.util.List;

public class ScaleAnimActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private VerticalAnimAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_anim);
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
        mAdapter = new VerticalAnimAdapter(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter.setItemData(mList);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        BaseItemAnimator itemAnimator = new ScaleItemAnimator(Benchmark.CENTER);
        itemAnimator.setChangeEnterAnimDuration(1000);
        itemAnimator.setChangeExitAnimDuration(1000);
        itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
        mRecyclerView.setItemAnimator(itemAnimator);
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
        BaseItemAnimator itemAnimator;
        switch (view.getId()) {
            case R.id.btn_center:
                itemAnimator = new ScaleItemAnimator(Benchmark.CENTER);
                itemAnimator.setChangeEnterAnimDuration(1000);
                itemAnimator.setChangeExitAnimDuration(1000);
                itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(itemAnimator);
                break;
            case R.id.btn_x:
                itemAnimator = new ScaleItemAnimator(Benchmark.X);
                itemAnimator.setChangeEnterAnimDuration(1000);
                itemAnimator.setChangeExitAnimDuration(1000);
                itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(itemAnimator);
                break;
            case R.id.btn_y:
                itemAnimator = new ScaleItemAnimator(Benchmark.Y);
                itemAnimator.setChangeEnterAnimDuration(1000);
                itemAnimator.setChangeExitAnimDuration(1000);
                itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(itemAnimator);
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
