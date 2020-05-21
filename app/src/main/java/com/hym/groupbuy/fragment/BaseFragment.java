package com.hym.groupbuy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hym.groupbuy.nohttp.HttpListner;
import com.hym.groupbuy.url.ContantsPool;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements ContantsPool, HttpListner<String> {
    private View mContentView;
    private Context mContext;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);
        mUnbinder = ButterKnife.bind(this, mContentView);
        mContext = getContext();
        init();
        initData();
        initView();
        return mContentView;
    }

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    /**
     * 一些View的相关操作
     */
    protected abstract void initView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void initData();

    /**
     * 做一些初始化的操作
     */
    protected abstract void init();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据 * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    protected void initIntent() {
    }

    public View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
