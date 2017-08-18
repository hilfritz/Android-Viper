package com.hilfritz.android.viper.ui.home.usecase;

import com.hilfritz.android.viper.application.interactor.BaseInteractor;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface GetCategoriesUseCase extends BaseInteractor{

    public interface Callback{
        void showList(ArrayList<Product> products);
        void showListRetrieveError(String str);
    }
}
