package com.zoopark.rv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zoopark.rv.RVAnimation.activity.RVAnimationHomeActivity;
import com.zoopark.rv.empty.EmptyViewActivity;
import com.zoopark.rvprovider.R;
import com.zoopark.rv.complex.ComplexPageActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/** Click *************************************************************************************/

	public void onEmptyViewClick(View view) {
		startActivity(new Intent(this, EmptyViewActivity.class));
	}

	public void onAnimationClick(View view) {
		startActivity(new Intent(this, RVAnimationHomeActivity.class));
	}

	public void onComplexPageClick(View view) {
		startActivity(new Intent(this, ComplexPageActivity.class));
	}
}
