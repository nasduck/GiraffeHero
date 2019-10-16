package com.zoopark.demo.complex;

import android.content.Context;

import com.zoopark.demo.complex.bean.ItemBean;
import com.zoopark.demo.complex.provider.ButtonGroupProvider;
import com.zoopark.demo.complex.provider.DivideProvider;
import com.zoopark.demo.complex.provider.ImageProvider;
import com.zoopark.demo.complex.provider.ProjectItemProvider;
import com.zoopark.demo.complex.provider.ProjectHeaderProvider;
import com.zoopark.rv.base.BaseAdapter;

import java.util.List;

public class ComplexAdapter extends BaseAdapter {

    private ImageProvider mImageProvider;
    private ButtonGroupProvider mButtonGroupProvider;

    private ProjectHeaderProvider mProjectHeaderProvider;
    private ProjectItemProvider mProjectItemProvider;

    private DivideProvider mDivideProvider;

    public ComplexAdapter(Context context) {
        super(context);

        mImageProvider = new ImageProvider(context);
        mButtonGroupProvider = new ButtonGroupProvider(context);

        mProjectHeaderProvider = new ProjectHeaderProvider(context, "已开发的组件库");
        mProjectItemProvider = new ProjectItemProvider(context);
        mProjectItemProvider.setHeader(mProjectHeaderProvider);

        mDivideProvider = new DivideProvider(context);

        finishInitialize();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProviders(
                mImageProvider,
                mButtonGroupProvider,
                mDivideProvider,
                mProjectItemProvider
        );
    }

    public void setOneItemData(List<ItemBean> list) {
        mProjectItemProvider.setData(list);
        this.notifySectionChanged(3);
    }

    public void setAddClickListener(ProjectHeaderProvider.OnAddClickListener listener) {
        mProjectHeaderProvider.setAddClickListener(listener);
    }
}
