package com.hilfritz.android.viper.data.sephoraApi;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductDetailsWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductsListWrapper;
import com.hilfritz.android.viper.util.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class SephoraProductRepositoryImpl implements SephoraProductRepository {

    Retrofit retrofit;
    SephoraProductRepository api;

    public SephoraProductRepositoryImpl() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient().newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())   //<<<----- EXCLUSIVE ONLY FOR THIS CLASS
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(SephoraApiConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //very important for RXJAVA
                .build();
        api = retrofit.create(SephoraProductRepository.class);
    }

    public SephoraProductRepository getApi() {
        return api;
    }

    @Override
    public Observable<CategoriesWrapper> getCategories() {
        return getApi().getCategories();
    }

    @Override
    public Observable<ProductsListWrapper> getProductsByPageAndCategory(int page, String cateogry) {
        return getApi().getProductsByPageAndCategory(page, cateogry);
    }

    @Override
    public Observable<ProductDetailsWrapper> getProductById(long id) {
        return getApi().getProductById(id);
    }
}
