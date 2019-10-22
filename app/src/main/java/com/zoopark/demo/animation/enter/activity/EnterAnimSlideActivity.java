package com.zoopark.demo.animation.enter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.enter.adapter.EnterAnimSlideAdapter;
import com.zoopark.rv.animation.enums.SlideDirection;

public class EnterAnimSlideActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private EnterAnimSlideAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_anim_slide);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        mAdapter = new EnterAnimSlideAdapter(this, SlideDirection.LEFT);
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
                mAdapter = new EnterAnimSlideAdapter(this, SlideDirection.LEFT);
                break;
            case R.id.btn_right:
                mAdapter = new EnterAnimSlideAdapter(this, SlideDirection.RIGHT);
                break;
            case R.id.btn_top:
                mAdapter = new EnterAnimSlideAdapter(this, SlideDirection.TOP);
                break;
            case R.id.btn_bottom:
                mAdapter = new EnterAnimSlideAdapter(this, SlideDirection.BOTTOM);
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifySectionChanged(0);
    }
}
