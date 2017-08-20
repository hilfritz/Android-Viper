package com.hilfritz.android.viper.ui.products.detail.interactor;

import com.hilfritz.android.viper.application.interactor.BaseInteractor;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

/**
 * Created by Hilfritz Camallere on 20/8/17.
 */

public interface GetProductDetailsUseCase extends BaseInteractor{
    interface Callback{
        void onRetrieveProductSuccess(Product product);
        void onRetrieveProductFail(String str);
        void onRetrieveProductFail(int strId);
        void addToCart(Product product);
        void removeFromCart(Product product);
        void incrementCartCount(Product product);
        void decrementCartCount(Product product);
    }
}
