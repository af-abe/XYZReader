package com.example.xyzreader;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by André on 08.05.2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
