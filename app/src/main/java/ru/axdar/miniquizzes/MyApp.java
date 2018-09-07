package ru.axdar.miniquizzes;

import android.support.multidex.MultiDexApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ildar2244 on 22.08.2018.
 */
public class MyApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        realmInit();
        super.onCreate();
    }

    private void realmInit() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
