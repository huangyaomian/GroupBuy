package com.hym.groupbuy.fragment;

import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.hym.groupbuy.R;
import com.hym.groupbuy.adapter.HomeBannerAdapter;
import com.hym.groupbuy.adapter.HomeFilmAdapter;
import com.hym.groupbuy.adapter.HomeGoodsAdapter;
import com.hym.groupbuy.adapter.HomeSortAdapter;
import com.hym.groupbuy.adapter.HomeSortItemAdapter;
import com.hym.groupbuy.bean.GoodsInfoBean;
import com.hym.groupbuy.bean.HomeFilmBean;
import com.hym.groupbuy.bean.HomeSortBean;
import com.hym.groupbuy.nohttp.CallServer;
import com.hym.groupbuy.widget.ViewPagerIndicator;
import com.hym.groupbuy.widget.WrapContentHeightViewPager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.home_film)
    TextView mHomeFilm;
    @BindView(R.id.home_film_rv)
    RecyclerView mHomeFilmRv;
    @BindView(R.id.home_film_ll)
    LinearLayout mHomeFilmLl;
    @BindView(R.id.home_recommend)
    TextView mHomeRecommend;
    @BindView(R.id.home_recommend_ll)
    LinearLayout mHomeRecommendLl;
    @BindView(R.id.home_sort_vp_indicator)
    ViewPagerIndicator homeSortVpIndicator;
    @BindView(R.id.home_refreshLayout)
    RefreshLayout  mHomeRefreshLayout;

    private List<Integer> srcList;
    private HomeBannerAdapter mHomeBannerAdapter;
    private List<View> viewList;
    private Gson mGson;

    private Request<String> recommendRequest;
    private Request<String> filmRequest;
    /**
     * 自定义的商品存放容器
     **/
    private List<GoodsInfoBean.GoodlistBean> mGoodslist;

    /**
     * 自定义的電影存放容器
     **/
    private List<HomeFilmBean.ResultBean> mFilmlist;


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
        homeSortVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                homeSortVpIndicator.setOffX(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        recommendRequest = NoHttp.createStringRequest(spRecommendURL, RequestMethod.GET);
        CallServer.getInstance().add(getActivity(), 0, recommendRequest, this, true, true);
        /**電影**/
        filmRequest = NoHttp.createStringRequest(spFilmURL, RequestMethod.GET);
        CallServer.getInstance().add(getActivity(), 1, filmRequest, this, true, true);
    }

    @Override
    protected void init() {
        viewList = new ArrayList<>();
        pagerOneSortList = new ArrayList<>();
        pagerTwoSortList = new ArrayList<>();
        mHomeBannerAdapter = new HomeBannerAdapter(srcList);
        mGoodslist = new ArrayList<>();
        mFilmlist = new ArrayList<>();
    }

    @Override
    protected void initEvent() {
        mHomeRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));//设置Header
        mHomeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                CallServer.getInstance().add(getActivity(), 0, recommendRequest, HomeFragment.this, true, true);
                CallServer.getInstance().add(getActivity(), 1, filmRequest, HomeFragment.this, true, true);
            }
        });

    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        mGson = new Gson();
        LinearLayoutManager layoutManager;
        switch (what) {
            case 0:
                mHomeRecommend.setVisibility(View.VISIBLE);
                GoodsInfoBean goodsInfo = mGson.fromJson(response.get(), GoodsInfoBean.class);
//                Log.d("onSucceed", response.get());
                List<GoodsInfoBean.GoodlistBean> goodsList = goodsInfo.getGoodlist();
                mGoodslist.clear();
                mGoodslist.addAll(goodsList);
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mHomeRv.setLayoutManager(layoutManager);
                HomeGoodsAdapter homeGoodsAdapter = new HomeGoodsAdapter(mGoodslist, getActivity());
                mHomeRv.setAdapter(homeGoodsAdapter);
                mHomeRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                // 设置数据后就要给RecyclerView设置点击事件
                homeGoodsAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // 这里本来是跳转页面 ，我们就在这里直接让其弹toast来演示
                        Toast.makeText(getActivity() , mGoodslist.get(position).getShort_title() , Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1:
                mHomeFilm.setVisibility(View.VISIBLE);
                Gson gson1 = new Gson();
                HomeFilmBean homeFilmBean = gson1.fromJson(response.get(), HomeFilmBean.class);
                Log.d("onSucceed", response.get());
                List<HomeFilmBean.ResultBean> result = homeFilmBean.getResult();
                mFilmlist.clear();
                mFilmlist.addAll(result);
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                mHomeFilmRv.setLayoutManager(layoutManager);
                HomeFilmAdapter homeFilmAdapter = new HomeFilmAdapter(mFilmlist, getActivity());
                mHomeFilmRv.setAdapter(homeFilmAdapter);
                // 设置数据后就要给RecyclerView设置点击事件
                homeFilmAdapter.setOnItemClickListener(new HomeFilmAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // 这里本来是跳转页面 ，我们就在这里直接让其弹toast来演示
                        Toast.makeText(getActivity() , mFilmlist.get(position).getFilmName() , Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
        mHomeRefreshLayout.finishRefresh();//结束刷新
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }



    /**
     * 定义RecyclerView选项单击事件的回调接口
     */
    public interface OnItemClickListener{
        //也可以不在这个activity或者是fragment中来声明接口，可以在项目中单独创建一个interface，就改成static就OK
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(RecyclerView parent,View view, int position, Map data);
        // void onItemLongClick(View view);类似，我这里没用就不写了
        //
        //这个data是List中放的数据类型，因为我这里是private List<Map> mapList;这样一个
        //然后我的每个item是这样的：
        //        HashMap map =new HashMap();
        //        map.put("img",R.drawable.delete);
        //        map.put("text","x1");
        //所以我的是map类型的，那如果是item中只有text的话比如List<String>，那么data就改成String类型
    }
}
