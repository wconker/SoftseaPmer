package com.android.softsea.adapter.MyAdapter;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.softsea.R;
import com.android.softsea.base.BaseHolder;
import com.android.softsea.callback.mClickInterface;
import com.android.softsea.entity.ProjectBean;
import com.android.softsea.entity.ProjectInfo;

/**
 * Created by softsea on 17/9/5.
 */

public class projectInfoHolder extends BaseHolder<ProjectInfo.DataBean> implements View.OnClickListener {


    private mClickInterface mclick;
    private TextView pro_title, pro_content, pro_time;


    public projectInfoHolder(View itemView) {
        super(itemView);
        pro_title = itemView.findViewById(R.id.pro_title);
        pro_content = itemView.findViewById(R.id.pro_content);
        pro_time = itemView.findViewById(R.id.pro_time);

    }

    @Override
    public void getData(ProjectInfo.DataBean d) {

        pro_content.setText(d.getSummary());
        pro_time.setText(d.getDate_submitted());
        pro_title.setText(d.getReportername());
    }


    public void setOnclick(mClickInterface CClickInterface) {
        mclick = CClickInterface;
    }

    @Override
    public void onClick(View view) {
        mclick.doClick();
    }
}
