package com.hilfritz.android.viper.ui.home.interactor;

import com.hilfritz.android.viper.application.interactor.BaseInteractor;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface GetCategoriesUseCase extends BaseInteractor {

    /**
     * NOTE: usecase calbacks should be implemented on both presenter and view(fragment/activity/dialog)
     */
    public interface Callback{
        void showList(ArrayList<Category> products);
        void showListRetrieveError(int stringId);
        void showListRetrieveError(String  string);
    }
}
