package com.hilfritz.android.viper.ui.home;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.cartRepository.CartManager;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.home.interactor.GetCategoriesUseCase;
import com.hilfritz.android.viper.ui.home.interactor.RetrieveSavedCartItemsUseCase;
import com.hilfritz.android.viper.ui.home.view.HomeView;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */
//// TODO: 19/8/17 there should be no interface for presenter, this should directly be implemented on the fragment
public interface HomePresenter extends GetCategoriesUseCase.Callback, RetrieveSavedCartItemsUseCase.Callback{
    void init(HomeView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository, CartManager cartManager);
    void populate();
    //// TODO: 19/8/17 make the view implement the presenter interface
    void showError(String str);
    void openCategoryProductList(String categoryName,int totalProductsInCategory);

    int getCategoryListSize();
    int getCartListSize();
    ArrayList<Category> getCategoryList();
    ArrayList<Product> getCartProductList();
    void populateCart();

}
