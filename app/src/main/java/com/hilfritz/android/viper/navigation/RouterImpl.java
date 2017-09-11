package com.hilfritz.android.viper.navigation;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.ui.home.entities.CategoriesContainer;
import com.hilfritz.android.viper.ui.products.detail.view.ProductDetailActivity;
import com.hilfritz.android.viper.ui.products.list.view.ProductListActivity;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class RouterImpl implements Router{
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_CATEGORY_TOTAL_PRODUCT_COUNT = "extraCategoryTotalProductCount";
    public static final String EXTRA_PRODUCT_ID = "extraProductId";
    public static final String EXTRA_CATEGORY_LIST = "extraCategoryList";
    Gson gson = new Gson();

    @Override
    public void openProductLists(Context context, String categoryName, int totalProductsInCategory, ArrayList<Category> categoryList) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_CATEGORY_TOTAL_PRODUCT_COUNT, totalProductsInCategory);
        CategoriesContainer container = new CategoriesContainer(categoryList);
        String categoriesStr = gson.toJson(container);
        intent.putExtra(EXTRA_CATEGORY_LIST, categoriesStr);
        context.startActivity(intent);
    }

    public static CategoriesContainer getCategoriesContainerFromIntent(Intent intent){
        String categoriesStr = intent.getStringExtra(EXTRA_CATEGORY_LIST);
        CategoriesContainer container = null;
        if (categoriesStr != null) {
            Gson gson = new Gson();
            container = gson.fromJson(categoriesStr, CategoriesContainer.class);
        }
        return container;
    }

    @Override
    public void openProductDetails(Context context, long productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        context.startActivity(intent);
    }
}
