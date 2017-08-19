package com.hilfritz.android.viper.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;

import com.hilfritz.android.viper.BuildConfig;
import com.hilfritz.android.viper.injection.component.AppComponent;
import com.hilfritz.android.viper.injection.component.DaggerAppComponent;
import com.hilfritz.android.viper.injection.module.CacheModule;
import com.hilfritz.android.viper.injection.module.PresenterModule;
import com.hilfritz.android.viper.injection.module.RestApiModule;
import com.hilfritz.android.viper.injection.module.SessionModule;
import com.hilfritz.android.viper.injection.module.UtilityModule;
import com.hilfritz.android.viper.util.logging.LogFileManager;
import com.hilfritz.android.viper.util.logging.TimberFileLogTree;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Created by Hilfritz P. Camallere on 6/4/2016.
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    //private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        //mInstance = this;
        initializeLeakCanary();
        initializeDagger();
        Fresco.initialize(this);
        initializeTimber();
    }

    private void initializeLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    private void initializeTimber() {
        //Timber.plant(new Timber.DebugTree());
        if (BuildConfig.DEBUG){
            Timber.plant(new TimberFileLogTree(new LogFileManager(this)));
            //Timber.plant(new Timber.DebugTree());
        }else{
            Timber.plant(new TimberFileLogTree(new LogFileManager(this)));
        }
    }

    private void initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .restApiModule(new RestApiModule())
                .presenterModule(new PresenterModule(this))
                .sessionModule(new SessionModule(this))
                .utilityModule(new UtilityModule())
                .cacheModule(new CacheModule())
                .build();

    }
    /*
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
    */

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
