package com.hilfritz.android.viper.navigation;

import android.content.Context;
import android.content.Intent;

import com.hilfritz.android.viper.ui.products.list.view.ProductListActivity;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class RouterImpl implements Router{
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_CATEGORY_TOTAL_PRODUCT_COUNT = "extraCategoryTotalProductCount";
    public static final String EXTRA_PRODUCT_ID = "extraProductId";

    @Override
    public void openProductLists(Context context, String categoryName, int totalProductsInCategory) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_CATEGORY_TOTAL_PRODUCT_COUNT, totalProductsInCategory);
        context.startActivity(intent);
    }

    @Override
    public void openProductDetails(Context context, long productId) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        context.startActivity(intent);
    }
}
