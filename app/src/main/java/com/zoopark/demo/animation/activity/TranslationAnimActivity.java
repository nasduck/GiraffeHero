package com.zoopark.demo.animation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.animation.adapter.HorizontalAnimAdapter;
import com.zoopark.demo.animation.adapter.VerticalAnimAdapter;
import com.zoopark.rv.animation.TranslationItemAnimator;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rvprovider.R;

import java.util.ArrayList;
import java.util.List;

public class TranslationAnimActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_translation_anim);
        // 绑定控件
        mRecyclerView = findViewById(R.id.recycler_view);
        mToolbar = findViewById(R.id.toolbar);

        // 设置标题栏
        setSupportActionBar(mToolbar);

        // 添加数据源
        for (int i = 0; i < 20; i++) {
            mList.add(i);
        }

        mLayoutManager = new LinearLayoutManager(this);
        mVerticalAdapter = new VerticalAnimAdapter(this);
        mHorizontalAdapter = new HorizontalAnimAdapter(this);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVerticalAdapter.setItemData(mList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mVerticalAdapter);
        // 默认从左侧进入
        mRecyclerView.setItemAnimator(new TranslationItemAnimator(SlideDirection.LEFT));
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
                // 切换列表方向，便于展示不同方向的进入动画
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
        switch (view.getId()) {
            case R.id.btn_left:
                mRecyclerView.setItemAnimator(new TranslationItemAnimator(SlideDirection.LEFT));
                break;
            case R.id.btn_right:
                mRecyclerView.setItemAnimator(new TranslationItemAnimator(SlideDirection.RIGHT));
                break;
            case R.id.btn_top:
                mRecyclerView.setItemAnimator(new TranslationItemAnimator(SlideDirection.TOP));
                break;
            case R.id.btn_bottom:
                mRecyclerView.setItemAnimator(new TranslationItemAnimator(SlideDirection.BOTTOM));
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
