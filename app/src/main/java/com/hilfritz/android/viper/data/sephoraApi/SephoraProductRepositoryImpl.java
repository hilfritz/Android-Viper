package com.hilfritz.android.viper.data.sephoraApi;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductDetailsWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductsListWrapper;

import rx.Observable;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class SephoraProductRepositoryImpl implements SephoraProductRepository {
    @Override
    public Observable<CategoriesWrapper> getCategories() {
        return null;
    }

    @Override
    public Observable<ProductsListWrapper> getProductsByPageAndCategory(long page, String cateogry) {
        return null;
    }

    @Override
    public Observable<ProductDetailsWrapper> getProductsById(long id) {
        return null;
    }
}
