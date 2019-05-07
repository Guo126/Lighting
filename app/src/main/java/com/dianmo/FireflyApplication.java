package com.dianmo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class FireflyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
