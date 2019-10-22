package com.zoopark.demo.animation.enter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zoopark.demo.R;

public class EnterAnimHomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_anim_home);

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

    public void onScaleAnimClick(View view) {
        startActivity(new Intent(this, EnterAnimScaleActivity.class));
    }

    public void onSlideAnimClick(View view) {
        startActivity(new Intent(this, EnterAnimSlideActivity.class));
    }
}
