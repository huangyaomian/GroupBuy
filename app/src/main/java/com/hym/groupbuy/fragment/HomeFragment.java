package com.hym.groupbuy.fragment;

import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.hym.groupbuy.MainActivity;
import com.hym.groupbuy.R;
import com.hym.groupbuy.adapter.HomeBannerAdapter;
import com.hym.groupbuy.adapter.HomeGoodsAdapter;
import com.hym.groupbuy.adapter.HomeSortAdapter;
import com.hym.groupbuy.adapter.HomeSortItemAdapter;
import com.hym.groupbuy.bean.GoodsInfoBean;
import com.hym.groupbuy.bean.HomeSortBean;
import com.hym.groupbuy.nohttp.CallServer;
import com.hym.groupbuy.widget.WrapContentHeightViewPager;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

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
    WrapContentHeightViewPager homeSortVp;
    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    private List<Integer> srcList;
    private HomeBannerAdapter mHomeBannerAdapter;
    private List<View> viewList;
    /**
     * 自定义的商品存放容器
     **/
    private List<GoodsInfoBean.GoodlistBean> mGoodslist;


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
        gridViewOne.setAdapter(new HomeSortItemAdapter(pagerOneSortList, getMContext()));
        gridViewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击监听
            }
        });

        View pagerTwo = getLayoutInflater().inflate(R.layout.pager_sort_home, null);
        GridView gridViewTwo = pagerTwo.findViewById(R.id.home_sort_item_gv);
        gridViewTwo.setAdapter(new HomeSortItemAdapter(pagerTwoSortList, getMContext()));
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
            if (i < 8) {
                pagerOneSortList.add(new HomeSortBean(iconName[i], typedArray.getResourceId(i, 0)));
            } else {
                pagerTwoSortList.add(new HomeSortBean(iconName[i], typedArray.getResourceId(i, 0)));
            }
        }
        /**猜你喜欢的请求**/
        Request<String> recommendRequest = NoHttp.createStringRequest(spRecommendURL, RequestMethod.GET);
        recommendRequest.addHeader(appId, appIdKey);
        recommendRequest.addHeader(APIId, APIIdKey);
        recommendRequest.addHeader(contentType, contentTypeKey);
        CallServer.getInstance().add(getActivity(), 0, recommendRequest, this, true, true);
    }

    @Override
    protected void init() {
        viewList = new ArrayList<>();
        pagerOneSortList = new ArrayList<>();
        pagerTwoSortList = new ArrayList<>();
        mHomeBannerAdapter = new HomeBannerAdapter(srcList);
        mGoodslist = new ArrayList<>();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        switch (what) {
            case 0:
                Gson gson = new Gson();
                GoodsInfoBean goodsInfo = gson.fromJson(response.get(), GoodsInfoBean.class);
                Log.d("onSucceed",response.get());
                List<GoodsInfoBean.GoodlistBean> goodlist = goodsInfo.getGoodlist();
                mGoodslist.addAll(goodlist);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mHomeRv.setLayoutManager(layoutManager);
                mHomeRv.setAdapter(new HomeGoodsAdapter(mGoodslist, getActivity()));
                mHomeRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                break;
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }
}
