package com.hilfritz.android.viper.dagger2.module;


import com.hilfritz.android.viper.application.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hilfritz P. Camallere on 6/28/2016.
 */
@Module
public class PresenterModule {


    private final MyApplication myApplication;

    public PresenterModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    /*
    //FRAGMENTS HERE
    @Provides
    @Singleton
    PlaceListPresenter providePlaceListPresenter(){
        return new PlaceListPresenter(myApplication);
    }
    */


}
