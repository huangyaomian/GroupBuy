package com.hym.groupbuy.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hym.groupbuy.R;
import com.hym.groupbuy.activity.BaseActivity;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * A simple {@link Fragment} subclass.
 *
 * create an instance of this fragment.
 */
public class AroundFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_around;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init() {

    }

    @Override
    public void onSucceed(int what, Response<String> response) {

    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }
}
