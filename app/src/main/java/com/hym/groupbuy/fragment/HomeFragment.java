package com.hym.groupbuy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hym.groupbuy.R;
import com.hym.groupbuy.adapter.HomeBannerAdapter;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.banner_home)
    BannerLayout mBannerHome;

    private List<Integer> srcList;
    private HomeBannerAdapter adapter;
    private List<View> viewList;

    public HomeFragment(List<Integer> srcList) {
        this.srcList = srcList;
    }

    public HomeFragment() {

    }


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        mBannerHome.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init() {
        viewList =  new ArrayList<>();
        adapter = new HomeBannerAdapter(srcList);
    }
}
