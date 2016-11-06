package com.pinterest.common;

import android.app.Application;

import com.application.imageloading.ImageLoader;
import com.pinterest.network.HttpManager;
import com.pinterest.network.RequestWrapper;

/**
 * Created by harsh on 05/11/16.
 */
public class PApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.initialize();
        RequestWrapper.initialize(this);
        ImageLoader.initiate(getApplicationContext());
    }
}
