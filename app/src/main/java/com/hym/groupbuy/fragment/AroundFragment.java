package com.hym.groupbuy.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.hym.groupbuy.R;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class AroundFragment extends BaseFragment {


    @BindView(R.id.around_toolbar)
    Toolbar aroundToolbar;
    @BindView(R.id.around_sort_iv)
    ImageView aroundSortIv;
    @BindView(R.id.around_sort_ll)
    LinearLayout aroundSortLl;
    @BindView(R.id.around_business_iv)
    ImageView aroundBusinessIv;
    @BindView(R.id.around_business_ll)
    LinearLayout aroundBusinessLl;
    @BindView(R.id.around_default_iv)
    ImageView aroundDefaultIv;
    @BindView(R.id.around_default_ll)
    LinearLayout aroundDefaultLl;
    @BindView(R.id.around_business_tv)
    TextView aroundBusinessTv;
    @BindView(R.id.around_default_tv)
    TextView aroundDefaultTv;
    @BindView(R.id.around_sort_tv)
    TextView aroundSortTv;
    private ListView popListLv;
    private PopupWindow popupMenu;


    private ArrayList<Map<String, String>> mMenuData1;
    private ArrayList<Map<String, String>> mMenuData2;
    private ArrayList<Map<String, String>> mMenuData3;
    private SimpleAdapter mMenuAdapter1;
    private SimpleAdapter mMenuAdapter2;
    private SimpleAdapter mMenuAdapter3;
    private int menuIndex = 0;

    public AroundFragment() {
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_around;
    }

    @Override
    protected void initView() {
        initPopMenu();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init() {
        mMenuData1 = new ArrayList<>();
        String[] menuStr1 = new String[]{"全部", "粮油", "衣服", "图书", "电子产品",
                "酒水饮料", "水果"};
        Map<String, String> map1;
        for (int i = 0, len = menuStr1.length; i < len; ++i) {
            map1 = new HashMap<String, String>();
            map1.put("name", menuStr1[i]);
            mMenuData1.add(map1);
        }

        mMenuData2 = new ArrayList<>();
        String[] menuStr2 = new String[]{"综合排序", "配送费最低"};
        Map<String, String> map2;
        for (int i = 0, len = menuStr2.length; i < len; ++i) {
            map2 = new HashMap<String, String>();
            map2.put("name", menuStr2[i]);
            mMenuData2.add(map2);
        }

        mMenuData3 = new ArrayList<>();
        String[] menuStr3 = new String[]{"优惠活动", "特价活动", "免配送费",
                "可在线支付"};
        Map<String, String> map3;
        for (int i = 0, len = menuStr3.length; i < len; ++i) {
            map3 = new HashMap<String, String>();
            map3.put("name", menuStr3[i]);
            mMenuData3.add(map3);
        }
    }

    /***
     * 初始化popupwindow
     */
    private void initPopMenu() {
        View popView = LayoutInflater.from(getActivity()).inflate(R.layout.popwin_supplier_list, null);
        popListLv = popView.findViewById(R.id.popwin_supplier_list_lv);
        popupMenu = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        popupMenu.setOutsideTouchable(true);
        popupMenu.setBackgroundDrawable(new BitmapDrawable());
        popupMenu.setFocusable(true);
        popupMenu.setAnimationStyle(R.style.popwin_anim_style);
        popupMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                aroundBusinessIv.setBackground(getResources().getDrawable(R.drawable.vector_drawable_collapse));
                aroundSortIv.setBackground(getResources().getDrawable(R.drawable.vector_drawable_collapse));
                aroundDefaultIv.setBackground(getResources().getDrawable(R.drawable.vector_drawable_collapse));
//                aroundSortTv.setTextColor(getResources().getColor(R.color.theme_blue));
//                aroundBusinessTv.setTextColor(getResources().getColor(R.color.theme_blue));
//                aroundDefaultTv.setTextColor(getResources().getColor(R.color.theme_blue));
            }
        });

        popView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupMenu.dismiss();
                    }
                });

        mMenuAdapter1 = new SimpleAdapter(getActivity(), mMenuData1, R.layout.item_rv_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mMenuAdapter2 = new SimpleAdapter(getActivity(), mMenuData2, R.layout.item_rv_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mMenuAdapter3 = new SimpleAdapter(getActivity(), mMenuData3, R.layout.item_rv_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});




        popListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupMenu.dismiss();
                switch (menuIndex) {
                    case 0:
                        String currentProduct = mMenuData1.get(i).get("name");
//                        mSupplierListTitleTv.setText(currentProduct);
                        aroundSortTv.setText(currentProduct);

                        break;
                    case 1:
                        String currentSort = mMenuData2.get(i).get("name");
//                        mSupplierListTitleTv.setText(currentSort);
                        aroundBusinessTv.setText(currentSort);
                        break;
                    case 2:
                        String currentAct = mMenuData3.get(i).get("name");
//                        mSupplierListTitleTv.setText(currentAct);
                        aroundDefaultTv.setText(currentAct);
                        break;
                }
            }
        });


    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onSucceed(int what, Response<String> response) {

    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }


    @OnClick({R.id.around_sort_ll, R.id.around_business_ll, R.id.around_default_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.around_sort_ll:
                aroundSortIv.setBackground(getResources().getDrawable(R.drawable.vector_drawable_unfold));
//                aroundSortTv.setTextColor(getResources().getColor(R.color.theme_blue));
                popListLv.setAdapter(mMenuAdapter1);
                popupMenu.showAsDropDown(aroundSortLl, 0, 0);
                menuIndex = 0;
                break;
            case R.id.around_business_ll:
                aroundBusinessIv.setBackground(getResources().getDrawable(R.drawable.vector_drawable_unfold));
//                aroundBusinessTv.setTextColor(getResources().getColor(R.color.theme_blue));
                popListLv.setAdapter(mMenuAdapter2);
                popupMenu.showAsDropDown(aroundBusinessLl, 0, 2);
                menuIndex = 1;
                break;
            case R.id.around_default_ll:
                aroundDefaultIv.setBackground(getResources().getDrawable(R.drawable.vector_drawable_unfold));
//                aroundDefaultTv.setTextColor(getResources().getColor(R.color.theme_blue));
                popListLv.setAdapter(mMenuAdapter3);
                popupMenu.showAsDropDown(aroundDefaultLl, 0, 2);
                menuIndex = 2;
                break;
        }
    }
}
