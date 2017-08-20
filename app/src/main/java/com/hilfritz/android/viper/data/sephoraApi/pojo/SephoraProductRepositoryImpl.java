package com.hilfritz.android.viper.data.sephoraApi.pojo;

import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductDetailsWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductsListWrapper;

import rx.Observable;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public class SephoraProductRepositoryImpl implements SephoraProductRepository{
    
    @Override
    public Observable<CategoriesWrapper> getCategories() {
        return null;
    }

    @Override
    public Observable<ProductsListWrapper> getProductsByPageAndCategory(int page, String cateogry) {
        return null;
    }

    @Override
    public Observable<ProductDetailsWrapper> getProductById(long id) {
        return null;
    }
}
