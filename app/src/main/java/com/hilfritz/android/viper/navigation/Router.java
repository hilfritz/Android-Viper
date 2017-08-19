package com.hilfritz.android.viper.navigation;

import android.content.Context;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface Router {
    void openProductLists(Context context, String categoryName, int totalProductsInCategory);
    void openProductDetails(Context context, long productId);
}
