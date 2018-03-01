package com.albertou.study.doubantop250;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by oujianfeng on 2018/3/1.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
