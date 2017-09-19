package com.android.softsea.ui.Center;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.softsea.R;
import com.android.softsea.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class rechargeFragment extends BaseFragment {


    public static rechargeFragment newInstance() {

        return new rechargeFragment();
    }




    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.ui_center_rechargefragment;
    }

}
