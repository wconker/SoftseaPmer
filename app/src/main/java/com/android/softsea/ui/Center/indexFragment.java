package com.android.softsea.ui.Center;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.softsea.R;
import com.android.softsea.adapter.IndexViewAdapter.ListAdapter;
import com.android.softsea.adapter.IndexViewAdapter.gvAdapter;
import com.android.softsea.base.BaseFragment;
import com.android.softsea.entity.Carousel.Picture;
import com.android.softsea.utils.MarginDecoration;
import com.kcode.autoscrollviewpager.view.AutoScrollViewPager;
import com.kcode.autoscrollviewpager.view.BaseViewPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class indexFragment extends BaseFragment {

    private BaseViewPagerAdapter<String> mBaseViewPagerAdapter;

    private AutoScrollViewPager mViewPager;
    private GridView gridView;
    private RecyclerView recyclerView;

    private String[] paths = {"http://www.dongpinyun.com/images/mob-bg.jpg",
            "http://www.dongpinyun.com/images/mob-bg.jpg",
    };

    public static indexFragment newInstance() {
        return new indexFragment();
    }

    @Override
    protected void initView(View view) {
        mViewPager = view.findViewById(R.id.viewPager);
        gridView = view.findViewById(R.id.gv);
        //轮播适配
        mBaseViewPagerAdapter = new BaseViewPagerAdapter<String>(getActivity(), listener) {
            @Override
            public void loadImage(ImageView view, int position, String url) {
                Picasso.with(getActivity()).load(url).into(view);
            }

            @Override
            public void setSubTitle(TextView textView, int position, String s) {

            }
        };
        mViewPager.setAdapter(mBaseViewPagerAdapter);
        mBaseViewPagerAdapter.add(initData());
        //gridview 适配
        gridView.setAdapter(new gvAdapter(getActivity(), gridData(), R.layout.utils_gridview_item));
        //recyc部分
        recyclerView = view.findViewById(R.id.cardRecyc);
        recyclerView.addItemDecoration(new MarginDecoration(getActivity()));
        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 7 || position == 8) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new ListAdapter(getActivity()));

    }

    @Override
    protected int getLayout() {
        return R.layout.ui_center_indexfragment;
    }

    private List<Picture> gridData() {
        List<Picture> gridData = new ArrayList<>();
        Picture picture;
        for (int i = 0; i < 12; i++) {
            picture = new Picture("", "生鲜", R.drawable.holder);
            gridData.add(picture);
        }
        return gridData;
    }

    private List<String> initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            data.add(paths[i]);
        }
        return data;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.onDestroy();
    }


    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<String>() {

        @Override
        public void onItemClick(int position, String url) {
            Toast.makeText(getActivity(),
                    position + " ========= " + url, Toast.LENGTH_SHORT).show();
        }
    };

}
