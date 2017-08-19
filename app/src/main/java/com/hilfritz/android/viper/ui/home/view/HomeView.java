package com.hilfritz.android.viper.ui.home.view;

import com.hilfritz.android.viper.ui.home.interactor.GetCategoriesUseCase;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface HomeView extends GetCategoriesUseCase.Callback{
    void showLoading();
    void hideLoading();
    void onCategoryClick(long categoryId);
    void openCategoryProductsPage(long categoryId);
    void showError(String str);
    void showCartCount(String str);

}
