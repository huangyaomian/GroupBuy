package com.hym.groupbuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.hym.groupbuy.MainActivity;
import com.hym.groupbuy.R;
import com.hym.groupbuy.adapter.MyGuideAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.guide_vp)
    ViewPager guideVp;
    @BindView(R.id.guide_btn)
    Button guideBtn;

    private List<View> mViewList;

    int[] imgRes = new int[]{
            R.mipmap.guide_1,
            R.mipmap.guide_2,
            R.mipmap.guide_3,
            R.mipmap.guide_4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        init();
        initView();
        initData();
        initEvent();


    }

    @Override
    public void init() {
        mViewList = new ArrayList<>();

    }

    @Override
    public void initView() {
        MyGuideAdapter adapter = new MyGuideAdapter(mViewList);
        guideVp.setAdapter(adapter);
    }

    @Override
    public void initData() {
        for (int i = 0; i < imgRes.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.guide_item, null);
            ImageView ivGuide = (ImageView)view.findViewById(R.id.guide_item_iv);
            ivGuide.setBackgroundResource(imgRes[i]);
            mViewList.add(view);
        }
    }

    @Override
    public void initEvent() {
        guideVp.setOnPageChangeListener(new Mylistner());
    }

    @OnClick({R.id.guide_vp, R.id.guide_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guide_vp:
                break;
            case R.id.guide_btn:
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

    class Mylistner implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //假如ViewPager滚动到最后一张就显示button
            if (position == mViewList.size()-1){
                guideBtn.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.anim_guide_btn_start);
                guideBtn.startAnimation(animation);

            }else{
                guideBtn.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
