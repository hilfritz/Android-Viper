package com.hilfritz.android.viper.ui.place.list.usecase;

import com.hilfritz.android.viper.application.interactor.BaseInteractor;

/**
 * Created by home on 8/6/2017.
 */

public interface FetchPlacesUsecase extends BaseInteractor{

    /*
    //USUALLY THE EXECUTIONCALLBACKS WILL CALL
    interface ExecutionCallbacks {
        void onRetrieveFailed();
        void onRetrieveSuccess();
    }
    */
    void showErrorInFetch();
    void showPlaceList();
}
