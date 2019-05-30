package com.zoopark.rv.RVAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zoopark.rv.animation.FlipItemAnimator;
import com.zoopark.rv.animation.enums.Benchmark;
import com.nasduck.rvprovider.R;

import java.util.ArrayList;
import java.util.List;

public class RVAnimationActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Integer> mList = new ArrayList<>();
    private RVAnimationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mRecyclerView = findViewById(R.id.recycler_view);

        for (int i = 0; i < 20; i++) {
            mList.add(i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new RVAnimationAdapter(this);
        mAdapter.setData(mList);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new FlipItemAnimator(Benchmark.X));

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
