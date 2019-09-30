package com.youdao.baselibrary.application;

import android.app.Application;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLifecycle();
    }

    private void initLifecycle() {
        registerActivityLifecycleCallbacks(new BaseLifecycleCallBack());
    }
}
