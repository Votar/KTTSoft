package com.t3.bertalt.kttsoft.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 */
public class App extends Application {

    private static final String TAG = "App";
    public static final String DB_NAME_KTTSOFT = "db_ktt_empl";

    @Override
    public void onCreate() {
        super.onCreate();


        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

        Stetho.initialize(Stetho.newInitializerBuilder(getApplicationContext())

                .enableDumpapp(Stetho.defaultDumperPluginsProvider(getApplicationContext()))

                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(getApplicationContext()))

                .build());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(DB_NAME_KTTSOFT)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);




    }


}
