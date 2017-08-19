package com.hilfritz.android.viper.injection.component;


import com.hilfritz.android.viper.injection.module.CacheModule;
import com.hilfritz.android.viper.injection.module.PresenterModule;
import com.hilfritz.android.viper.injection.module.RestApiModule;
import com.hilfritz.android.viper.injection.module.SessionModule;
import com.hilfritz.android.viper.injection.module.UtilityModule;
import com.hilfritz.android.viper.ui.home.view.HomeFragment;
import com.hilfritz.android.viper.ui.place.list.view.PlaceListFragment;
import com.hilfritz.android.viper.ui.products.detail.view.ProductDetailFragment;
import com.hilfritz.android.viper.ui.products.list.view.ProductListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Hilfritz P. Camallere on 6/4/2016.
 */
@Singleton
@Component(modules = {
        RestApiModule.class,
        PresenterModule.class,
        UtilityModule.class,
        SessionModule.class,
        CacheModule.class
})
public interface AppComponent {
    void inject(PlaceListFragment fragment);
    void inject(HomeFragment fragment);
    void inject(ProductListFragment fragment);
    void inject(ProductDetailFragment fragment);
    /*

    void inject(PlaceListPresenter presenter);
    void inject(BaseActivity baseActivity);
    void inject(SimpleDialog simpleDialog);
    */

    //void inject(ImageCache imageCache);
    //void inject(LogFileManager logFileManager);

}
