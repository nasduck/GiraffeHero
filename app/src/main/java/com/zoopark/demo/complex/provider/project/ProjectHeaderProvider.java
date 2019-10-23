package com.zoopark.demo.complex.provider.project;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.provider.BaseSectionHeaderFooter;
import com.zoopark.rv.base.holder.BaseViewHolder;
import com.zoopark.rv.base.model.IndexPath;

public class ProjectHeaderProvider extends BaseSectionHeaderFooter<String> {

    private String mTitle;

    public ProjectHeaderProvider(Context context, String title) {
        super(context);
        this.mTitle = title;
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_project_header;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setText(R.id.tv_title, mTitle);
    }
}
