package com.sww.myapplication;

import android.app.Application;

import com.sww.myapplication.utils.OkHttpUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtil.init();
    }

}
