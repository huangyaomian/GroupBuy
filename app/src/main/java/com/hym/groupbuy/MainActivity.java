package com.hym.groupbuy;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hym.groupbuy.activity.BaseActivity;
import com.hym.groupbuy.fragment.AroundFragment;
import com.hym.groupbuy.fragment.HomeFragment;
import com.hym.groupbuy.fragment.MineFragment;
import com.hym.groupbuy.fragment.MoreFragment;
import com.hym.groupbuy.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private int lastIndex;
    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        mFragments = new ArrayList<>();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mFragments.add(new HomeFragment());
        mFragments.add(new AroundFragment());
        mFragments.add(new MineFragment());
        mFragments.add(new MoreFragment());
        // 初始化展示MessageFragment
        setFragmentPosition(0);
    }

    @Override
    public void initEvent() {

    }


    @OnClick(R.id.nav_view)
    public void onViewClicked() {
    }

    public void initBottomNavigation() {
        // 解决当item大于三个时，非平均布局问题
        BottomNavigationViewHelper.disableShiftMode(navView);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setFragmentPosition(0);
                        break;
                    case R.id.navigation_around:
                        setFragmentPosition(1);
                        break;
                    case R.id.navigation_mine:
                        setFragmentPosition(2);
                        break;
                    case R.id.navigation_more:
                        setFragmentPosition(3);
                        break;
                    default:
                        break;
                }
                // 这里注意返回true,否则点击失效
                return true;
            }
        });
    }

    private void setFragmentPosition(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.ll_frameLayout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }
}
