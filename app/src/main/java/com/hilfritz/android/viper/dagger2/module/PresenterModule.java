package com.hilfritz.android.viper.dagger2.module;


import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.ui.home.HomePresenter;
import com.hilfritz.android.viper.ui.home.HomePresenterImpl;
import com.hilfritz.android.viper.ui.place.list.PlaceListPresenterImpl;
import com.hilfritz.android.viper.ui.place.list.PlacesListPresenter;
import com.hilfritz.android.viper.ui.products.detail.ProductDetailPresenter;
import com.hilfritz.android.viper.ui.products.detail.ProductDetailPresenterImpl;
import com.hilfritz.android.viper.ui.products.list.ProductListPresenter;
import com.hilfritz.android.viper.ui.products.list.ProductListPresenterImpl;

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
    //FRAGMENTS HERE
    @Provides
    @Singleton
    PlacesListPresenter providePlaceListPresenter(){
        return new PlaceListPresenterImpl();
    }

    //FRAGMENTS HERE
    @Provides
    @Singleton
    HomePresenter provideHomePresenter(){
        return new HomePresenterImpl();
    }

    @Provides
    @Singleton
    ProductListPresenter provideProductListPresenter(){
        return new ProductListPresenterImpl();
    }

    @Provides
    @Singleton
    ProductDetailPresenter provideProductDetailPresenter(){
        return new ProductDetailPresenterImpl();
    }

}
