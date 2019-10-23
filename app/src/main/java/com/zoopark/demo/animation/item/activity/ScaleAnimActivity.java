package com.zoopark.demo.animation.item.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.item.adapter.VerticalAnimAdapter;
import com.zoopark.rv.animation.item.BaseItemAnimator;
import com.zoopark.rv.animation.item.ScaleItemAnimator;
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

        mRecyclerView = findViewById(R.id.recycler_view);
        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        // fake data
        for (int i = 0; i < 20; i++) {
            mList.add(i);
        }

        // init adapter
        mAdapter = new VerticalAnimAdapter(this);
        mAdapter.setItemData(mList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        BaseItemAnimator animator = new ScaleItemAnimator(Benchmark.CENTER);
        animator.setChangeEnterAnimDuration(1000);
        animator.setChangeExitAnimDuration(1000);
        animator.setChangeEnterAnimDelay(animator.getChangeExitAnimDuration());
        mRecyclerView.setItemAnimator(animator);
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
        BaseItemAnimator animator;
        switch (view.getId()) {
            case R.id.btn_center:
                animator = new ScaleItemAnimator(Benchmark.CENTER);
                animator.setChangeEnterAnimDuration(1000);
                animator.setChangeExitAnimDuration(1000);
                animator.setChangeEnterAnimDelay(animator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(animator);
                break;
            case R.id.btn_x:
                animator = new ScaleItemAnimator(Benchmark.X);
                animator.setChangeEnterAnimDuration(1000);
                animator.setChangeExitAnimDuration(1000);
                animator.setChangeEnterAnimDelay(animator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(animator);
                break;
            case R.id.btn_y:
                animator = new ScaleItemAnimator(Benchmark.Y);
                animator.setChangeEnterAnimDuration(1000);
                animator.setChangeExitAnimDuration(1000);
                animator.setChangeEnterAnimDelay(animator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(animator);
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

    public void onChange(View view) {
        mList.set(5, 10000);
        mAdapter.notifyItemChanged(5);
    }


}
