package com.zoopark.rv.oreo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nasduck.rvprovider.R;

public class OreoActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private OreoAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oreo);

		mRecyclerView = findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		mAdapter = new OreoAdapter(this);
		mRecyclerView.setAdapter(mAdapter);
	}
}
