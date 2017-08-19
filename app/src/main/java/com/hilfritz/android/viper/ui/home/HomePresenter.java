package com.hilfritz.android.viper.ui.home;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.ui.home.interactor.GetCategoriesUseCase;
import com.hilfritz.android.viper.ui.home.view.HomeView;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */
//// TODO: 19/8/17 there should be no interface for presenter, this should directly be implemented on the fragment
public interface HomePresenter extends GetCategoriesUseCase.Callback{
    void init(HomeView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository);
    void populate();
    //// TODO: 19/8/17 make the view implement the presenter interface
    void showError(String str);
    void showCartCount(String str);
    void openCategoryProductList(long categoryId);

}
