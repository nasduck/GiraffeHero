package com.zoopark.demo.animation.enter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.enter.adapter.EnterAnimAdapter;
import com.zoopark.rv.animation.enums.SlideDirection;

public class EnterAnimSlideActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private EnterAnimAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_anim_slide);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        mAdapter = new EnterAnimAdapter(this);
        mAdapter.setAnimationType(2);
        mAdapter.setSlideDirection(SlideDirection.LEFT);
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
            case R.id.btn_left:
                mAdapter.setSlideDirection(SlideDirection.LEFT);
                break;
            case R.id.btn_right:
                mAdapter.setSlideDirection(SlideDirection.RIGHT);
                break;
            case R.id.btn_top:
                mAdapter.setSlideDirection(SlideDirection.TOP);
                break;
            case R.id.btn_bottom:
                mAdapter.setSlideDirection(SlideDirection.BOTTOM);
                break;
        }
        mAdapter.notifySectionChanged(0);
    }
}
