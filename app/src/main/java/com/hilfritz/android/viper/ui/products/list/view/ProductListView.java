package com.hilfritz.android.viper.ui.products.list.view;

import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.products.list.interactor.GetProductsUseCase;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface ProductListView extends GetProductsUseCase.Callback{
    void showLoading();
    void hideLoading();
    void onProductClick(Product product);
    void openProductDetailPage(Product product);
    void showError(String str);
}
