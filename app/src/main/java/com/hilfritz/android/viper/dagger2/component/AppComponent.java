package com.hilfritz.android.viper.dagger2.component;


import com.hilfritz.android.viper.dagger2.module.CacheModule;
import com.hilfritz.android.viper.dagger2.module.PresenterModule;
import com.hilfritz.android.viper.dagger2.module.RestApiModule;
import com.hilfritz.android.viper.dagger2.module.SessionModule;
import com.hilfritz.android.viper.dagger2.module.UtilityModule;

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
    /*
    void inject(PlaceListFragment fragment);
    void inject(PlaceListPresenter presenter);
    void inject(BaseActivity baseActivity);
    void inject(SimpleDialog simpleDialog);
    */

    //void inject(ImageCache imageCache);
    //void inject(LogFileManager logFileManager);

}
