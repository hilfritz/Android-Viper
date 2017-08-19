package com.hilfritz.android.viper.ui.home;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.ui.home.interactor.GetCategoriesUseCase;
import com.hilfritz.android.viper.ui.home.interactor.GetCategoriesUseCaseImpl;
import com.hilfritz.android.viper.ui.home.view.HomeView;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public class HomePresenterImpl implements HomePresenter{
    ThreadProvider threadProvider;
    SephoraProductRepository sephoraProductRepository;
    HomeView view;
    @Override
    public void init(HomeView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository) {
        this.threadProvider = threadProvider;
        this.sephoraProductRepository = sephoraProductRepository;
        this.view = view;
    }


    @Override
    public void populate() {
        view.showLoading();
        GetCategoriesUseCase getCategoriesUseCase = new GetCategoriesUseCaseImpl(
                threadProvider.getIoThread(),
                threadProvider.getMainThread(),
                sephoraProductRepository,
                this
                );
        getCategoriesUseCase.run();
        view.hideLoading();
    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void showCartCount(String str) {

    }

    @Override
    public void openCategoryProductList(long categoryId) {
        view.openCategoryProductsPage(categoryId);
    }

    @Override
    public void showList(ArrayList<Category> products) {
        view.showList(products);
    }

    @Override
    public void showListRetrieveError(int stringId) {
        view.showListRetrieveError(stringId);
    }

    @Override
    public void showListRetrieveError(String str) {
        view.showListRetrieveError(str);
    }
}
