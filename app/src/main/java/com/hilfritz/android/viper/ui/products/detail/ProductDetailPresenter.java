package com.hilfritz.android.viper.ui.products.detail;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.cartRepository.CartManager;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.ui.products.detail.interactor.GetProductDetailsUseCase;
import com.hilfritz.android.viper.ui.products.detail.view.ProductDetailView;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface ProductDetailPresenter extends GetProductDetailsUseCase.Callback{
    void init(ProductDetailView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository, CartManager cartManager, long productId);
    void populate();
    void showError(String str);
}
