package com.android.softsea.adapter.MyAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.softsea.R;
import com.android.softsea.base.BaseHolder;
import com.android.softsea.entity.note.WorkBean;

import java.util.List;

/**
 * Created by wukanghui on 2017/9/2.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<BaseHolder> {

    private Context mContext;
    private List<WorkBean.DataBean> dataSource;

    public MyRecyclerViewAdapter(Context context, List<WorkBean.DataBean> data) {
        this.dataSource = data;
        this.mContext = context;


    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e("Conker",i+"");
        BaseHolder<WorkBean.DataBean> baseHolder= new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ui_note_notelist_item, viewGroup, false));
        return baseHolder;
    }

    @Override
    public void onBindViewHolder(BaseHolder baseHolder, int i) {

        baseHolder.getData(dataSource.get(i));

    }

    @Override
    public int getItemCount() {
        return dataSource.size() / 2;
    }
}
