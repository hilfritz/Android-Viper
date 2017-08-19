package com.hilfritz.android.viper.injection.module;

import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hilfritz P. Camallere on 6/4/2016.
 */
@Module
public class RestApiModule {

    @Provides
    @Singleton
    SephoraProductRepository provideSephoraApi(){
        return new SephoraProductRepositoryImpl();
    }


}
