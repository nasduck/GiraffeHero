package com.zoopark.demo.complex;

import android.content.Context;

import com.zoopark.demo.complex.bean.ItemBean;
import com.zoopark.demo.complex.provider.ButtonGroupProvider;
import com.zoopark.demo.complex.provider.DivideProvider;
import com.zoopark.demo.complex.provider.ImageProvider;
import com.zoopark.demo.complex.provider.OneItemProvider;
import com.zoopark.demo.complex.provider.TitleProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class ComplexAdapter extends BaseAdapter {

    private ImageProvider mImageProvider;
    private ButtonGroupProvider mButtonGroupProvider;

    private TitleProvider mFirstTitleProvider;
    private OneItemProvider mOneItemProvider;

    private DivideProvider mDivideProvider;

    public ComplexAdapter(Context context) {
        super(context);

        mImageProvider = new ImageProvider(context);
        mButtonGroupProvider = new ButtonGroupProvider(context);

        mFirstTitleProvider = new TitleProvider(context);
        mFirstTitleProvider.setTitle("已开发的组件库");
        mOneItemProvider = new OneItemProvider(context);

        mDivideProvider = new DivideProvider(context);

        finishInitialize();

    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(mImageProvider, mButtonGroupProvider, mDivideProvider,
                mFirstTitleProvider, mOneItemProvider);
    }

    public void setOneItemData(List<ItemBean> list) {
        mOneItemProvider.setData(list);
        this.notifySectionChanged(4);
    }

    public void setAddClickListener(TitleProvider.OnAddClickListener listener) {
        mFirstTitleProvider.setAddClickListener(listener);
    }
}
