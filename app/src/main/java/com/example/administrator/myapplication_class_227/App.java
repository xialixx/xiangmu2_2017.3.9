package com.example.administrator.myapplication_class_227;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2017/3/6.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG=true;
        UMShareAPI.get(this);
    }
    {
        PlatformConfig.setWeixin("111", "111");
        PlatformConfig.setQQZone("1106024944", "QP3J5MELekaTpnN6");
        PlatformConfig.setSinaWeibo("111", "111", "111");
    }
}
