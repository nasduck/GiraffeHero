package com.zoopark.demo.animation.change.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;

/**
 * Home page for animation demo
 */
public class HomeAnimActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_anim);

        mToolbar = findViewById(R.id.toolbar);
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
        startActivity(new Intent(this, FadeAnimActivity.class));
    }

    public void onFlipAnimClick(View view) {
        startActivity(new Intent(this, FlipAnimActivity.class));
    }

    public void onScaleAnimClick(View view) {
        startActivity(new Intent(this, ScaleAnimActivity.class));
    }

    public void onTranslationClick(View view) {
        startActivity(new Intent(this, TranslationAnimActivity.class));
    }
}
