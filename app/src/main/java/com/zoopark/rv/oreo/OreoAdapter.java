package com.zoopark.rv.oreo;

import android.content.Context;

import com.zoopark.rv.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yi on 2019/4/10.
 * Description:
 */
public class OreoAdapter extends BaseAdapter {

	private OreoProvider mOreoProvider;

	private List<String> mTasteList;

	public OreoAdapter(Context context) {
		super(context);

		initTastes();

		mOreoProvider = new OreoProvider(context);
		mOreoProvider.setTasteList(mTasteList);
		finishInitialize();
	}

	@Override
	public void registerItemProvider() {
		mProviderDelegate.registerProvider(mOreoProvider);
	}

	public void initTastes() {
		mTasteList = new ArrayList<>();
		mTasteList.add("Cholocate");
		mTasteList.add("Matcha");
		mTasteList.add("Strawberry");
		mTasteList.add("Ice cream");

	}
}
