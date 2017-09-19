package com.android.softsea.adapter.IndexViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.softsea.R;
import com.android.softsea.base.BaseHolder;
import com.android.softsea.callback.mClickInterface;
import com.android.softsea.ui.Good.GoodInfo;

import java.util.List;

/**
 * Created by softsea on 17/8/10.
 */

public class ListAdapter extends RecyclerView.Adapter<BaseHolder> implements mClickInterface {
    private Context context;
    private List<String> list;
    private final int TITLE = 0;
    private final int BOX = 1;

    public ListAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case TITLE:
                return new listHolder(LayoutInflater.from(context).inflate(R.layout.ui_good_goodlist_item, null, false));
            case BOX:
                return new listHolder(LayoutInflater.from(context).inflate(R.layout.ui_good_goodlist_item, null, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseHolder baseHolder, int i) {

        ((listHolder) baseHolder).setOnclick(this);

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 7 || position == 8) {
            return TITLE;
        } else {
            return BOX;
        }


    }

    @Override
    public void doClick() {
        this.context.startActivity(new Intent(this.context, GoodInfo.class));
    }

    @Override
    public void doClick(int pos, View view) {

    }
}
