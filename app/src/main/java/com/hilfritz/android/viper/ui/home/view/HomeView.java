package com.hilfritz.android.viper.ui.home.view;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.ui.home.interactor.GetCategoriesUseCase;
import com.hilfritz.android.viper.ui.home.interactor.RetrieveSavedCartItemsUseCase;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface HomeView extends GetCategoriesUseCase.Callback, RetrieveSavedCartItemsUseCase.Callback{
    void showLoading();
    void hideLoading();
    void onCategoryClick(String categoryName, int totalProductsInCategory);
    void openCategoryProductsPage(String categoryName, int totalProductsInCategory, ArrayList<Category> categoryList);
    void showError(String str);

}
