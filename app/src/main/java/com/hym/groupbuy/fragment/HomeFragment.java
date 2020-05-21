package com.hym.groupbuy.fragment;

import android.content.res.TypedArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.hym.groupbuy.R;
import com.hym.groupbuy.adapter.HomeBannerAdapter;
import com.hym.groupbuy.adapter.HomeSortAdapter;
import com.hym.groupbuy.adapter.HomeSortItemAdapter;
import com.hym.groupbuy.bean.HomeSortBean;
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
    @BindView(R.id.home_sort_vp)
    ViewPager homeSortVp;

    private List<Integer> srcList;
    private HomeBannerAdapter mHomeBannerAdapter;
    private List<View> viewList;

    /**
     * gridview 两页的数据
     */
    private List<HomeSortBean> pagerOneSortList;
    private List<HomeSortBean> pagerTwoSortList;

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
        mBannerHome.setAdapter(mHomeBannerAdapter);

        initSortGridView();
    }

    private void initSortGridView() {
        View pagerOne = getLayoutInflater().inflate(R.layout.pager_sort_home, null);
        GridView gridViewOne = pagerOne.findViewById(R.id.home_sort_item_gv);
        gridViewOne.setAdapter(new HomeSortItemAdapter(pagerOneSortList,getMContext()));
        gridViewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击监听
            }
        });

        View pagerTwo = getLayoutInflater().inflate(R.layout.pager_sort_home, null);
        GridView gridViewTwo = pagerTwo.findViewById(R.id.home_sort_item_gv);
        gridViewTwo.setAdapter(new HomeSortItemAdapter(pagerTwoSortList,getMContext()));
        gridViewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击监听
            }
        });

        viewList.add(pagerOne);
        viewList.add(pagerTwo);
        homeSortVp.setAdapter(new HomeSortAdapter(viewList));
    }

    @Override
    protected void initData() {
        initSortData();
    }

    private void initSortData() {
        String[] iconName = getResources().getStringArray(R.array.home_bar_labels);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.home_bar_icon);
        for (int i = 0; i < iconName.length; i++) {
            if (i<8){
                pagerOneSortList.add(new HomeSortBean(iconName[i],typedArray.getResourceId(i,0)));
            }else{
                pagerTwoSortList.add(new HomeSortBean(iconName[i],typedArray.getResourceId(i,0)));
            }
        }
    }

    @Override
    protected void init() {
        viewList = new ArrayList<>();
        pagerOneSortList = new ArrayList<>();
        pagerTwoSortList = new ArrayList<>();
        mHomeBannerAdapter = new HomeBannerAdapter(srcList);

    }
}
