package com.hilfritz.android.viper.data.sephoraApi;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductDetailsWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductsListWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface SephoraProductRepository {

    @GET(SephoraApiConstants.CATEGORIES_URL)
    Observable<CategoriesWrapper> getCategories();

    @GET(SephoraApiConstants.PRODUCTS_URL)
    Observable<ProductsListWrapper> getProductsByPageAndCategory(
            @Query("page") int page,
            @Query("category") String cateogry);

    @GET(SephoraApiConstants.PRODUCT_DETAIL_URL)
    Observable<ProductDetailsWrapper> getProductsById(@Path("id") long id);


}
