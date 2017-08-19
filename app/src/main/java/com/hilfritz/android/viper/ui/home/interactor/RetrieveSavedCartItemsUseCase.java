package com.hilfritz.android.viper.ui.home.interactor;

import com.hilfritz.android.viper.application.interactor.BaseInteractor;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface RetrieveSavedCartItemsUseCase extends BaseInteractor {
    public interface Callback{
        void showSavedCartList(ArrayList<Product> products);
        void showCartRetrieveError(int stringId);
        void showCartRetrieveError(String  string);
    }
}
