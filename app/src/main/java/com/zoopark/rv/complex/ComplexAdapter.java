package com.zoopark.rv.complex;

import android.content.Context;
import android.os.IBinder;

import com.zoopark.rv.BaseAdapter;
import com.zoopark.rv.complex.bean.ItemBean;
import com.zoopark.rv.complex.provider.ButtonGroupProvider;
import com.zoopark.rv.complex.provider.DivideProvider;
import com.zoopark.rv.complex.provider.ImageProvider;
import com.zoopark.rv.complex.provider.OneItemProvider;
import com.zoopark.rv.complex.provider.TitleProvider;
import com.zoopark.rv.complex.provider.TwoItemsProvider;

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
