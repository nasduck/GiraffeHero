package com.zoopark.demo.notify.section.provider;

import android.content.Context;

import com.zoopark.demo.R;
import com.zoopark.rv.base.BaseSectionHeaderFooter;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;

public class SectionFooterProvider extends BaseSectionHeaderFooter {

    public SectionFooterProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_section_footer;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {

    }
}
