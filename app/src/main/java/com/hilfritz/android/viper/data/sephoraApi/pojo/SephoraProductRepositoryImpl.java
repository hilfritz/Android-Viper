package com.hilfritz.android.viper.data.sephoraApi.pojo;

import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductDetailsWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductsListWrapper;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public class SephoraProductRepositoryImpl implements SephoraProductRepository{
    
    @Override
    public CategoriesWrapper getCategories() {
        return null;
    }

    @Override
    public ProductsListWrapper getProductsByPageAndCategory(long page, String cateogry) {
        return null;
    }

    @Override
    public ProductDetailsWrapper getProductsById(long id) {
        return null;
    }
}
