package com.zoopark.demo.home;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.zoopark.demo.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private HomeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRecyclerView = findViewById(R.id.recycler_view);

		mAdapter = new HomeAdapter(this);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.setLayoutManager(layoutManager);

		callGetData();
	}

	private void callGetData() {
		List<FunctionBean> list = new ArrayList<>();
		list.add(new FunctionBean(getResources().getString(R.string.recycler_view_complex), R.drawable.ic_complex));
		list.add(new FunctionBean(getResources().getString(R.string.recycler_view_empty_view), R.drawable.ic_empty));
		list.add(new FunctionBean(getResources().getString(R.string.recycler_view_notify_decoration), R.drawable.ic_notify_decoration));
		list.add(new FunctionBean(getResources().getString(R.string.recycler_view_notify_section), R.drawable.ic_notify_section));
		list.add(new FunctionBean(getResources().getString(R.string.recycler_view_enter_animation), R.drawable.ic_animation_enter));
		list.add(new FunctionBean(getResources().getString(R.string.recycler_view_change_animation), R.drawable.ic_animayion_change));
		mAdapter.setData(list);
	}

}
