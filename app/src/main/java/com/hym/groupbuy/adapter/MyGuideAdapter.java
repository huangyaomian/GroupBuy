package com.hym.groupbuy.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面viewpager适配器
 */

public class MyGuideAdapter extends PagerAdapter {

    private List<View> mViewList = new ArrayList<>();

    public MyGuideAdapter(List<View> mViewList){
        super();
        this.mViewList = mViewList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViewList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public int getCount() {
//        return mViewList.size();
        return mViewList == null?0:mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;//官方推荐的写法
    }
}
