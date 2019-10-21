package com.zoopark.demo.notify.section.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.demo.notify.section.bean.SectionBean;
import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

import java.util.ArrayList;
import java.util.List;

public class SectionItemProvider extends BaseItemProvider<List<SectionBean>> {

    private List<SectionBean> mList;

    public SectionItemProvider(Context context) {
        super(context);
        mList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_section_item;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_title, mList.get(indexPath.getRow()).getTitle());
        holder.setText(R.id.tv_content, mList.get(indexPath.getRow()).getContent());
    }

    @Override
    public void setData(List<SectionBean> newData) {
        super.setData(newData);
        mList = newData;
    }
}
