package com.zoopark.rv.RVAnimation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.rvprovider.R;

public class RVAnimationHomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_animation_home);
        // 绑定控件
        mToolbar = findViewById(R.id.toolbar);
        // 设置标题栏
        setSupportActionBar(mToolbar);
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

    public void onFadeAnimClick(View view) {
        Intent intent = new Intent(this, RVAnimationFadeActivity.class);
        startActivity(intent);
    }

    public void onFlipAnimClick(View view) {
        Intent intent = new Intent(this, RVAnimationFlipActivity.class);
        startActivity(intent);
    }

    public void onScaleAnimClick(View view) {
        Intent intent = new Intent(this, RVAnimationScaleActivity.class);
        startActivity(intent);
    }

    public void onTranslationClick(View view) {
        Intent intent = new Intent(this, RVAnimationTranslationActivity.class);
        startActivity(intent);
    }
}
