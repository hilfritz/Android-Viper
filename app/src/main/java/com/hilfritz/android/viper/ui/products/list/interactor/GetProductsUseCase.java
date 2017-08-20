package com.hilfritz.android.viper.ui.products.list.interactor;

import com.hilfritz.android.viper.application.interactor.BaseInteractor;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface GetProductsUseCase extends BaseInteractor {
    void incrementPage();
    void resetPage();
    void incrementLoadedCounterBy(int size);
    int getLoadedProductsCount();


    /**
     * NOTE: usecase calbacks should be implemented on both presenter and view(fragment/activity/dialog)
     */
    public interface Callback{
        void showProductList(ArrayList<Product> products);
        void showProductListRetrieveError(String str);
        void showProductListRetrieveError(int stringId);
        void loadMoreFinish();
        void loadMore();
    }
}
