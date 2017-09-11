package com.hilfritz.android.viper.navigation;

import android.content.Context;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface Router {
    void openProductLists(Context context, String categoryName, int totalProductsInCategory, ArrayList<Category> categoryList);
    void openProductDetails(Context context, long productId);
}
