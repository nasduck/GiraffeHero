package com.zoopark.demo.empty;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zoopark.demo.R;
import com.zoopark.rv.empty.OnEmptyViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo for empty view
 */
public class EmptyViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private EmptyViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_view);

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);

        mAdapter = new EmptyViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create empty view
        ImageView emptyView = new ImageView(this);
        emptyView.setImageResource(R.drawable.ic_no_content);
        emptyView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        // Set empty view
        mAdapter.setEmptyView(emptyView, new OnEmptyViewListener() {
            @Override
            public void onEmptyViewClick() {
                Toast.makeText(EmptyViewActivity.this, getResources().getText(R.string.empty_click_message), Toast.LENGTH_SHORT).show();
            }
        });
        // or you can user your own layout
        //mAdapter.setEmptyView(R.layout.layout_empty_view, mRecyclerView);
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

    //** Click ***********************************************************************************//

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
