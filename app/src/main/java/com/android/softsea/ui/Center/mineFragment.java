package com.android.softsea.ui.Center;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.softsea.R;
import com.android.softsea.base.BaseFragment;
import com.android.softsea.adapter.commonAdapter.CommonAdapter;
import com.android.softsea.adapter.commonAdapter.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class mineFragment extends BaseFragment {


    private List<String> mData = new ArrayList<>();
    @Bind(R.id.gv)
    GridView gv;

    public static mineFragment newInstance() {

        return new mineFragment();
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.ui_center_minefragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mData.add("售后规则");
        mData.add("客服电话");
        mData.add("加盟合作");
        gv.setAdapter(new CommonAdapter<String>(getActivity(), mData, R.layout.common_gv_layout) {

            @Override
            public void setViewContent(CommonViewHolder viewHolder, String s) {

                viewHolder.setText(R.id.title, s);

            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
