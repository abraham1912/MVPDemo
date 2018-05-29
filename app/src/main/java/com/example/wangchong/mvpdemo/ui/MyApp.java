package com.example.wangchong.mvpdemo.ui;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class MyApp extends MultiDexApplication {
    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();

    }
}
