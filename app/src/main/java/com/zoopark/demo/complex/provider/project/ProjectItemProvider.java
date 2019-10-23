package com.zoopark.demo.complex.provider.project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.zoopark.demo.R;
import com.zoopark.rv.animation.enter.BaseInAnimation;
import com.zoopark.rv.animation.enter.SlideInAnimation;
import com.zoopark.rv.animation.enums.SlideDirection;
import com.zoopark.rv.base.BaseItemProvider;
import com.zoopark.rv.base.BaseViewHolder;
import com.zoopark.rv.base.IndexPath;
import com.zoopark.demo.complex.bean.ProjectBean;

import java.util.ArrayList;
import java.util.List;

public class ProjectItemProvider extends BaseItemProvider<List<ProjectBean>> {

    private List<ProjectBean> mList;

    public ProjectItemProvider(Context context) {
        super(context);
        mList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getLayout() {
        return R.layout.item_complex_project_item;
    }

    @Override
    public void onBind(BaseViewHolder holder, IndexPath indexPath) {
        holder.setClick(R.id.container, this, indexPath);
        holder.setImageResource(R.id.iv_project_logo, mList.get(indexPath.getRow()).getImageId());
        holder.setText(R.id.tv_content, mList.get(indexPath.getRow()).getContent());
    }

    @Override
    public void setData(List<ProjectBean> newData) {
        this.mList = newData;
    }

    @Override
    public void onClick(BaseViewHolder holder, IndexPath indexPath, int viewId) {
        Intent intent= new Intent();
        switch (indexPath.getRow()) {
            case 0:
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/nasduck/RafikiPermissions"));
                mContext.startActivity(intent);
                break;
            case 1:
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/nasduck/GiantPandaDialog"));
                mContext.startActivity(intent);
                break;
            case 2:
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://github.com/nasduck/LesserPandaToast"));
                mContext.startActivity(intent);
                break;
            default:
                Toast.makeText(mContext, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public BaseInAnimation getAnimator() {
        return new SlideInAnimation(SlideDirection.RIGHT);
    }
}
