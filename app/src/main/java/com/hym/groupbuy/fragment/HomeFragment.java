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
 *
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private BannerLayout mBannerHome;
    private List<Integer> srcList;
    private HomeBannerAdapter adapter;

    public HomeFragment(List<Integer> srcList) {
        this.srcList = srcList;
    }

    public HomeFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mBannerHome = view.findViewById(R.id.banner_home);

        adapter = new HomeBannerAdapter(srcList);
        mBannerHome.setAdapter(adapter);
        return view;
    }
}
