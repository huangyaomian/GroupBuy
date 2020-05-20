package com.hym.groupbuy.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hym.groupbuy.MainActivity;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private Context mContext;

    public MainAdapter(FragmentManager fm, Context context, List<Fragment> listFragment){
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mContext = context;
        this.mFragmentList = listFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
