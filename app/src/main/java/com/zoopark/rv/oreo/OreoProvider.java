package com.zoopark.rv.oreo;

import android.content.Context;
import android.view.View;

import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;
import com.zoopark.rvprovider.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yi on 2019/4/10.
 * Description:
 */
public class OreoProvider extends BaseItemProvider {

	private List<String> mTasteList;
	private List<Boolean> mIsVisibleList;

	public OreoProvider(Context context) {
		super(context);

		mIsVisibleList = new ArrayList<>();
	}

	@Override
	public int getLayout() {
		return R.layout.item_oreo;
	}

	@Override
	public void onBind(BaseViewHolder holder, IndexPath indexPath) {
		String taste = mTasteList.get(indexPath.getRow());
		holder.setText(R.id.tv_taste, taste);
		holder.setClick(R.id.btn_visible, this, indexPath);
		holder.setClick(R.id.btn_gone, this, indexPath);
	}

	@Override
	public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
		switch (viewId) {
			case R.id.btn_visible:
				mIsVisibleList.set(indexPath.getRow(), !mIsVisibleList.get(indexPath.getRow()));
				if (mIsVisibleList.get(indexPath.getRow())) {
					holder.setText(R.id.btn_visible, mContext.getResources().getString(R.string.invisible));
					holder.setVibility(R.id.tv_taste, View.VISIBLE);
				} else {
					holder.setText(R.id.btn_visible, mContext.getResources().getString(R.string.visible));
					holder.setVibility(R.id.tv_taste, View.INVISIBLE);
				}
				break;
			case R.id.btn_gone:
				holder.setVibility(R.id.tv_taste, View.GONE);
				break;
			default:
				break;
		}
	}

	@Override
	public int getItemCount() {
		return mTasteList.size();
	}

	public void setTasteList(List<String> tasteList) {
		mTasteList = tasteList;
		for (int i = 0; i < mTasteList.size(); i+=1) {
			mIsVisibleList.add(Boolean.TRUE);
		}
	}
}
