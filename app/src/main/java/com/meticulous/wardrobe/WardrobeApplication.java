package com.meticulous.wardrobe;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by c74241 on 8/29/17.
 */

public class WardrobeApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static WardrobeApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
