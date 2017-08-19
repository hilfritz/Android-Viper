package com.hilfritz.android.viper.injection.module;


import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.application.thread.ThreadProviderImpl;
import com.hilfritz.android.viper.data.cartRepository.CartManager;
import com.hilfritz.android.viper.data.cartRepository.CartManagerImpl;
import com.hilfritz.android.viper.data.session.SessionData;
import com.hilfritz.android.viper.navigation.Router;
import com.hilfritz.android.viper.navigation.RouterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hilfritz P. Camallere on 7/2/2016.
 */
@Module
public class SessionModule {
    MyApplication myApplication;

    public SessionModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Singleton
    @Provides
    SessionData provideSessionData(){
        return new SessionData(this.myApplication);
    }

    @Singleton
    @Provides
    ThreadProvider provideThreadProvider(){
        return new ThreadProviderImpl(Schedulers.io(), AndroidSchedulers.mainThread());
    }

    @Singleton
    @Provides
    Router provideRouter(){
        return new RouterImpl();
    }
    @Singleton
    @Provides
    CartManager provideCartManager(){
        return new CartManagerImpl();
    }


}
