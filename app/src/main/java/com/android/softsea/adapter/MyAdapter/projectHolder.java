package com.android.softsea.adapter.MyAdapter;

import android.net.wifi.aware.PublishConfig;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.softsea.R;
import com.android.softsea.base.BaseHolder;
import com.android.softsea.callback.mClickInterface;
import com.android.softsea.entity.ProjectBean;
import com.android.softsea.entity.note.ProBean;

/**
 * Created by softsea on 17/9/5.
 */

public class projectHolder extends BaseHolder<ProjectBean.DataBean> implements View.OnClickListener {

    Button vText;
    private int mpos=0;
    private mClickInterface mclick;
    LinearLayout textParent;
    View mview;

    public projectHolder(View itemView) {
        super(itemView);
        mview=itemView;
        vText = itemView.findViewById(R.id.proName);
        textParent = itemView.findViewById(R.id.proNameParent);
        vText.setOnClickListener(this);
    }
    @Override
    public void getData(ProjectBean.DataBean d) {
        vText.setText(d.getName());
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) textParent.getLayoutParams();
        lp.height = 100 + 7 * d.getName().length();
        textParent.setLayoutParams(lp);
    }

    public void getPost(int pos) {
        this.mpos=pos;
    }
    public void setOnclick(mClickInterface CClickInterface) {
        mclick = CClickInterface;
    }

    @Override
    public void onClick(View view) {
        mclick.doClick(this.mpos,vText);
    }
}
