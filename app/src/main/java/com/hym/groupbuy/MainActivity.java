package com.hym.groupbuy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hym.groupbuy.activity.BaseActivity;
import com.hym.groupbuy.adapter.MainAdapter;
import com.hym.groupbuy.fragment.AroundFragment;
import com.hym.groupbuy.fragment.HomeFragment;
import com.hym.groupbuy.fragment.MineFragment;
import com.hym.groupbuy.fragment.MoreFragment;
import com.hym.groupbuy.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.mian_viewpager)
    ViewPager mMianViewpager;

    //广告条的图片资源
    List<Integer> imgResList;

    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        init();
        initView();
        initData();
        initEvent();
    }

    @Override
    public void init() {
        mFragments = new ArrayList<>();
        imgResList = new ArrayList<>();
        initBottomNavigation();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        //廣告圖片的添加
        imgResList.add(R.mipmap.banner01);
        imgResList.add(R.mipmap.banner02);
        imgResList.add(R.mipmap.banner03);

        mFragments.add(new HomeFragment(imgResList));
        mFragments.add(new AroundFragment());
        mFragments.add(new MineFragment());
        mFragments.add(new MoreFragment());
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), this, mFragments);
        mMianViewpager.setAdapter(mainAdapter);

    }

    @Override
    public void initEvent() {
        mMianViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //注意这个方法滑动时会调用多次，下面是参数解释：
                //position当前所处页面索引,滑动调用的最后一次绝对是滑动停止所在页面
                //positionOffset:表示从位置的页面偏移的[0,1]的值。
                //positionOffsetPixels:以像素为单位的值，表示与位置的偏移
            }

            @Override
            public void onPageSelected(int position) {
                //该方法只在滑动停止时调用，position滑动停止所在页面位置
//                当滑动到某一位置，导航栏对应位置被按下
                navView.getMenu().getItem(position).setChecked(true);
                //这里使用navigation.setSelectedItemId(position);无效，
                //setSelectedItemId(position)的官网原句：Set the selected
                // menu item ID. This behaves the same as tapping on an item
                //未找到原因
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//这个方法在滑动是调用三次，分别对应下面三种状态
// 这个方法对于发现用户何时开始拖动，
// 何时寻呼机自动调整到当前页面，或何时完全停止/空闲非常有用。
//                state表示新的滑动状态，有三个值：
//                SCROLL_STATE_IDLE：开始滑动（空闲状态->滑动），实际值为0
//                SCROLL_STATE_DRAGGING：正在被拖动，实际值为1
//                SCROLL_STATE_SETTLING：拖动结束,实际值为2
            }
        });
    }


    public void initBottomNavigation() {
        // 解决当item大于三个时，非平均布局问题
        BottomNavigationViewHelper.disableShiftMode(navView);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mMianViewpager.setCurrentItem(0);
                        break;
                    case R.id.navigation_around:
                        mMianViewpager.setCurrentItem(1);
                        break;
                    case R.id.navigation_mine:
                        mMianViewpager.setCurrentItem(2);
                        break;
                    case R.id.navigation_more:
                        mMianViewpager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
                // 这里注意返回true,否则点击失效
                return true;
            }
        });
    }

    @Override
    public void onCreateCustomToolbar(Toolbar toolbar) {
        super.onCreateCustomToolbar(toolbar);
        toolbar.showOverflowMenu();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
