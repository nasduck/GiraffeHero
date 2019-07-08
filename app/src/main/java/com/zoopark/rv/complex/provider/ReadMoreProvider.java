package com.zoopark.rv.complex.provider;

import android.content.Context;
import android.widget.TextView;

import com.zoopark.rvprovider.R;
import com.zoopark.rv.BaseItemProvider;
import com.zoopark.rv.BaseViewHolder;
import com.zoopark.rv.IndexPath;

public class ReadMoreProvider extends BaseItemProvider<Object, BaseViewHolder> {

    private boolean isNoContent;
    private TextView mTvNoContent;

    public ReadMoreProvider(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_read_more;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        mTvNoContent = holder.getView(R.id.tv_read_more);
        if (isNoContent) {
            holder.setText(R.id.tv_read_more, "暂无相关内容");
            mTvNoContent.setCompoundDrawables(null, null, null, null);
        } else {
            holder.setClick(R.id.layout, this, indexPath);
        }
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        switch (indexPath.getSection()) {
            case 19:
                break;
            case 23:
                break;
        }
    }

    public void setReadMoreStatus(boolean isNoContent) {
        this.isNoContent = isNoContent;
    }
}