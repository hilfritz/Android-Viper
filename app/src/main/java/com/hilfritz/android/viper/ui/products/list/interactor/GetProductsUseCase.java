package com.hilfritz.android.viper.ui.products.list.interactor;

import com.hilfritz.android.viper.application.interactor.BaseInteractor;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface GetProductsUseCase extends BaseInteractor {
    void incrementPage();
    void resetPage();
    void incrementLoadedCounterBy(int size);
    int getLoadedProductsCount();

}
