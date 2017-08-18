package com.hilfritz.android.viper.ui.home;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.home.usecase.GetCategoriesUseCase;
import com.hilfritz.android.viper.ui.home.usecase.GetCategoriesUseCaseImpl;
import com.hilfritz.android.viper.ui.home.view.HomeView;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public class HomePresenterImpl implements HomePresenter, GetCategoriesUseCase.Callback{
    ThreadProvider threadProvider;
    SephoraProductRepository sephoraProductRepository;
    @Override
    public void init(HomeView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository) {
        this.threadProvider = threadProvider;
        this.sephoraProductRepository = sephoraProductRepository;
    }


    @Override
    public void populate() {
        GetCategoriesUseCase getCategoriesUseCase = new GetCategoriesUseCaseImpl(
                threadProvider.getIoThread(),
                threadProvider.getMainThread(),
                sephoraProductRepository,
                this
                );
        getCategoriesUseCase.run();
    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void showCartCount(String str) {

    }

    @Override
    public void showList(ArrayList<Product> products) {

    }

    @Override
    public void showListRetrieveError(String str) {

    }
}
