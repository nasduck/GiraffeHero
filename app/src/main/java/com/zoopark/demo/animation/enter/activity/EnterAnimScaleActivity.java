package com.zoopark.demo.animation.enter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.enter.adapter.EnterAnimScaleAdapter;
import com.zoopark.demo.animation.enter.adapter.EnterAnimSlideAdapter;
import com.zoopark.rv.animation.enums.Benchmark;

public class EnterAnimScaleActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private EnterAnimScaleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_anim_scale);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        mAdapter = new EnterAnimScaleAdapter(this, Benchmark.CENTER);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
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

    public void onAnimTypeClick(View view) {
        switch (view.getId()) {
            case R.id.btn_center:
                mAdapter = new EnterAnimScaleAdapter(this, Benchmark.CENTER);
                break;
            case R.id.btn_x:
                mAdapter = new EnterAnimScaleAdapter(this, Benchmark.X);
                break;
            case R.id.btn_y:
                mAdapter = new EnterAnimScaleAdapter(this, Benchmark.Y);
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifySectionChanged(0);
    }
}
