package com.timetson.theheartofegypt.modules;

import android.app.Application;
import android.content.Context;

public class TheHeartOfEgypt extends Application {

    private static Context context;

    public static Context getAppContext() {
        return TheHeartOfEgypt.context;
    }

    @Override
    public void onCreate() {
        TheHeartOfEgypt.context = getApplicationContext();
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
