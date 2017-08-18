package com.hilfritz.android.viper.data.sephoraApi;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductDetailsWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductsListWrapper;

import rx.Observable;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface SephoraProductRepository {
    Observable<CategoriesWrapper> getCategories();
    Observable<ProductsListWrapper> getProductsByPageAndCategory(long page, String cateogry);
    Observable<ProductDetailsWrapper> getProductsById(long id);


}
