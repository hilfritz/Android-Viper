package com.hilfritz.android.viper.dagger2.module;


import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.data.session.ImageCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = SessionModule.class
)
public class CacheModule {
    @Singleton
    @Provides
    ImageCache provideImageCache(MyApplication myApplication){
        return new ImageCache(myApplication);
    }
}
