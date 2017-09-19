package com.android.softsea.adapter.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.softsea.R;
import com.android.softsea.callback.mClickInterface;
import com.android.softsea.entity.ProjectBean;
import com.android.softsea.entity.ProjectInfo;
import com.android.softsea.ui.Pro.ScrollingActivity;

import java.util.List;

/**
 * Created by softsea on 17/9/5.
 */

public class ProjectInfoAdapter extends RecyclerView.Adapter<projectInfoHolder> implements mClickInterface {
    Context mcontext;
    List<ProjectInfo.DataBean> mlist;
    List<Integer> mheight;

    public ProjectInfoAdapter(Context context, List<ProjectInfo.DataBean> list) {
        mcontext = context;
        mlist = list;

    }

    @Override
    public int getItemCount() {

        return mlist.size();
    }

    @Override
    public projectInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new projectInfoHolder(LayoutInflater.from(mcontext).inflate(R.layout.ui_project_info_item, parent, false));
    }

    @Override
    public void onBindViewHolder(projectInfoHolder holder, int position) {

        holder.getData(mlist.get(position));
        holder.setOnclick(this);
    }

    @Override
    public void doClick() {

    }

    @Override
    public void doClick(int pos, View view) {

    }


//    public void add(int pos) {
//        mlist.add(pos, ProjectBean);
//        mheight.add((int) (100 + Math.random() * 300));
//        notifyItemInserted(pos);
//    }
//
//
//    public void del(int pos) {
//        mlist.remove(pos);
//        notifyItemRemoved(pos);
//    }


}