package com.zoopark.demo.animation.enter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.enter.adapter.EnterHybridAnimAdapter;

public class EnterAnimHybridActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private EnterHybridAnimAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_anim_hybrid);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        mAdapter = new EnterHybridAnimAdapter(this);
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
