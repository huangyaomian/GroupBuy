package com.hym.groupbuy.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.hym.groupbuy.R;
import com.hym.groupbuy.utils.ToolbarHelper;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.StatusBarUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private ToolbarHelper toolbarHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XUI.initTheme(this);
        StatusBarUtils.initStatusBarStyle(this,false, ActivityCompat.getColor(this, R.color.theme_blue));
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        toolbarHelper = new ToolbarHelper(layoutResID,BaseActivity.this);

        Toolbar toolbar = toolbarHelper.getToolbar();

        setContentView(toolbarHelper.getmContentView());

        setSupportActionBar(toolbar);

        //**//**自定义一些自己的操作**//**//
        onCreateCustomToolbar(toolbar);

    }

    public void onCreateCustomToolbar(Toolbar toolbar) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public abstract void init();
    public abstract void initView();
    public abstract void initData();
    public abstract void initEvent();
}
