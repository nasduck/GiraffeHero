package com.zoopark.demo.animation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;
import com.zoopark.demo.animation.adapter.HorizontalAnimAdapter;
import com.zoopark.demo.animation.adapter.VerticalAnimAdapter;
import com.zoopark.rv.animation.BaseItemAnimator;
import com.zoopark.rv.animation.FadeItemAnimator;
import com.zoopark.rv.animation.enums.SlideDirection;

import java.util.ArrayList;
import java.util.List;

public class FadeAnimActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private VerticalAnimAdapter mVerticalAdapter;
    private HorizontalAnimAdapter mHorizontalAdapter;
    private LinearLayoutManager mLayoutManager;

    private boolean isVertical = true;      // 判断列表当前方向

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_anim);

        mRecyclerView = findViewById(R.id.recycler_view);
        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        // fake data
        for (int i = 0; i < 20; i++) {
            mList.add(i);
        }

        // init layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // init adapter
        mVerticalAdapter = new VerticalAnimAdapter(this);
        mVerticalAdapter.setItemData(mList);
        mHorizontalAdapter = new HorizontalAnimAdapter(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mVerticalAdapter);

        // init animation. enter from left
        BaseItemAnimator animator = new FadeItemAnimator(SlideDirection.LEFT);
        animator.setChangeEnterAnimDuration(1000);
        animator.setChangeExitAnimDuration(1000);
        animator.setChangeEnterAnimDelay(animator.getChangeExitAnimDuration());
        mRecyclerView.setItemAnimator(animator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_direction:
                // Switch list direction to show different animation
                if (isVertical) {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_view_horizontal_white_24dp));
                    mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mHorizontalAdapter.setItemData(mList);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mHorizontalAdapter);
                } else {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_view_vertical_white_24dp));
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mVerticalAdapter.setItemData(mList);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mVerticalAdapter);
                }
                isVertical = !isVertical;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Click *************************************************************************************/

    public void onAnimTypeClick(View view) {
        BaseItemAnimator itemAnimator;
        switch (view.getId()) {
            case R.id.btn_left:
                itemAnimator = new FadeItemAnimator(SlideDirection.LEFT);
                itemAnimator.setChangeEnterAnimDuration(1000);
                itemAnimator.setChangeExitAnimDuration(1000);
                itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(itemAnimator);
                break;
            case R.id.btn_right:
                itemAnimator = new FadeItemAnimator(SlideDirection.RIGHT);
                itemAnimator.setChangeEnterAnimDuration(1000);
                itemAnimator.setChangeExitAnimDuration(1000);
                itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(itemAnimator);
                break;
            case R.id.btn_top:
                itemAnimator = new FadeItemAnimator(SlideDirection.TOP);
                itemAnimator.setChangeEnterAnimDuration(1000);
                itemAnimator.setChangeExitAnimDuration(1000);
                itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(itemAnimator);
                break;
            case R.id.btn_bottom:
                itemAnimator = new FadeItemAnimator(SlideDirection.BOTTOM);
                itemAnimator.setChangeEnterAnimDuration(1000);
                itemAnimator.setChangeExitAnimDuration(1000);
                itemAnimator.setChangeEnterAnimDelay(itemAnimator.getChangeExitAnimDuration());
                mRecyclerView.setItemAnimator(itemAnimator);
                break;
        }
    }

    public void onAddClick(View view) {
        mList.add(6, 20000);
        if (isVertical) {
            mVerticalAdapter.notifyItemInserted(6);
        } else {
            mHorizontalAdapter.notifyItemInserted(6);
        }
    }

    public void onRemove(View view) {
        mList.remove(6);
        if (isVertical) {
            mVerticalAdapter.notifyItemRemoved(6);
        } else {
            mHorizontalAdapter.notifyItemRemoved(6);
        }
    }

    public void onMove(View view) {
        if (isVertical) {
            mVerticalAdapter.notifyItemMoved(4, 6);
        } else {
            mHorizontalAdapter.notifyItemMoved(4, 6);
        }
    }

    public void onChange(View view) {
        mList.set(5, 10000);
        if (isVertical) {
            mVerticalAdapter.notifyItemChanged(5);
        } else {
            mHorizontalAdapter.notifyItemChanged(5);
        }
    }
}
