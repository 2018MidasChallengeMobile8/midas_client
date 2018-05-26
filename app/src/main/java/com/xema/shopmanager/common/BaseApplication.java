package com.xema.shopmanager.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.xema.shopmanager.data.RestClient;
import com.xema.shopmanager.data.RetroService;
import com.xema.shopmanager.model.My;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by xema0 on 2018-02-10.
 */

public class BaseApplication extends Application {
    public static boolean DEBUG = false;
    public static  RetroService service;
    public static RestClient<RetroService> client = new RestClient<>();
    public static My my;


    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: 2018-02-19 마이그레이션 설정파일 추가(https://realm.io/docs/java/latest/#migrations)
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        service = client.getClient(RetroService.class);

        SharedPreferences mPref = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(mPref.contains("User") || my == null)
        {
            Log.d("my_home","이미 db에 있다면");
            Gson gson = new Gson();
            String json = mPref.getString("User","");
            my = gson.fromJson(json,My.class);
        }
        else {
            my = new My("1", "1", "a");
            SharedPreferences.Editor prefEditor = mPref.edit();
            Gson gson = new Gson();
            String json = gson.toJson(my);
            prefEditor.putString("User", json);
            prefEditor.commit();
        }

        //벡터 이미지 활성화
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        DEBUG = isDebuggable(this);
        if (DEBUG)
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
    }

    private boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }

        return debuggable;
    }
}
