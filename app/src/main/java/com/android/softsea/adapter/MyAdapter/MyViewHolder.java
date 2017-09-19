package com.android.softsea.adapter.MyAdapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.softsea.R;
import com.android.softsea.base.BaseHolder;
import com.android.softsea.entity.note.WorkBean;

/**
 * Created by wukanghui on 2017/9/2.
 */

public class MyViewHolder extends BaseHolder<WorkBean.DataBean> {

    private View item;

    MyViewHolder(View itemView) {
        super(itemView);
        item = itemView;
    }

    @Override
    public void getData(WorkBean.DataBean d) {


        Log.e("cONKER", d.getCname());
        TextView textView = item.findViewById(R.id.name);
        TextView contenttext = item.findViewById(R.id.contentText);
        TextView time = item.findViewById(R.id.time);
        textView.setText(d.getCname());
        contenttext.setText(d.getContent());
        time.setText(d.getCtime());
    }


}
