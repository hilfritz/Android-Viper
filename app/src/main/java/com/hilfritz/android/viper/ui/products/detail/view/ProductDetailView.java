package com.hilfritz.android.viper.ui.products.detail.view;

import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.products.detail.interactor.GetProductDetailsUseCase;

/**
 * Created by Hilfritz Camallere on 20/8/17.
 */

public interface ProductDetailView extends GetProductDetailsUseCase.Callback{
    void showLoading();
    void hideLoading();
    void showProductDetails(Product product);
    void showError(String str);
    void showError(int strId);
    void showAddToCartButton();
    void hideAddToCartButton();
}
