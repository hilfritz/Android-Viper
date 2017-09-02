package com.hilfritz.android.viper.ui.products.list.view;

import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface ProductListView{
    void showLoading();
    void hideLoading();
    void onProductClick(Product product);
    void openProductDetailPage(Product product);
    void showError(String str);
    void showError(int strId);

    void showLoadMore();
    void hideLoadMore();
    void notifyList();
}
