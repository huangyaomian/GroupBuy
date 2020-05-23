package com.hym.groupbuy.app;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xuexiang.xui.XUI;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

import cn.bmob.v3.Bmob;

public class App extends Application {

    private String APP_KEY = "489ffacb02eec736c4889cafb736b726";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bmob
        Bmob.initialize(this, APP_KEY);
        XUI.init(this); //初始化UI框架
        //XUI.debug(true);  //开启UI框架调试日志

        //初始化百度sdk
        SDKInitializer.initialize(this);
        //設置坐標類型
        SDKInitializer.setCoordType(CoordType.BD09LL);

        MultiDex.install(this);

        //nohttp
        InitializationConfig initializationConfig = InitializationConfig.newBuilder(this)
                .addHeader("X-Bmob-Application-Id","596a1dc9e9617aedee7505a214dc30b9")
                .addHeader("X-Bmob-REST-API-Key","ebea31aa16b2c1bb1045d9eb967e4494")
                .addHeader("Content-Typ","application/json")
                .connectionTimeout(30*1000)
                .readTimeout(30*1000)
                .build();
        NoHttp.initialize(initializationConfig);
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 打印Log的tag。

        //初始化fresco
        Fresco.initialize(this);
    }
}
