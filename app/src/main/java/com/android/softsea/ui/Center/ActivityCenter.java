package com.android.softsea.ui.Center;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.softsea.R;
import com.android.softsea.base.BaseActivity;
import com.android.softsea.entity.tabs.TabEntity;
import com.android.softsea.utils.ViewFindUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;


public class ActivityCenter extends BaseActivity {
    private View mDecorView;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "分类", "充值", "购物车", "我的"};
    public CommonTabLayout mTabLayout;


    @Override
    public void setButterKnife() {

    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_center;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        setFragmentList();

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], R.drawable.cart, R.drawable.cart));
        }
        mDecorView = getWindow().getDecorView();
        mTabLayout = ViewFindUtils.find(mDecorView, R.id.tl);
        mTabLayout.setTabData(mTabEntities, this, R.id.container, mFragments);
    }

    private void setFragmentList() {
        mFragments.add(indexFragment.newInstance());
        mFragments.add(categroyFragment.newInstance());
        mFragments.add(rechargeFragment.newInstance());
        mFragments.add(shoppingcardFragment.newInstance());
        mFragments.add(mineFragment.newInstance());
    }


}
