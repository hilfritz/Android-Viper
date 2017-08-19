package com.hilfritz.android.viper.ui.home;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
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
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Product> cartProducts = new ArrayList<>();

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
        view.showError(str);
    }


    @Override
    public void openCategoryProductList(String categoryName,int totalProductsInCategory) {
        view.openCategoryProductsPage(categoryName, totalProductsInCategory);
    }

    @Override
    public int getCategoryListSize() {
        return getCategoryList().size();
    }

    @Override
    public int getCartListSize() {
        return getCartProductList().size();
    }

    @Override
    public ArrayList<Category> getCategoryList() {
        return categories;
    }

    @Override
    public ArrayList<Product> getCartProductList() {
        return cartProducts;
    }

    @Override
    public void showCategoryList(ArrayList<Category> categories) {
        getCategoryList().clear();
        getCategoryList().addAll(categories);
        view.showCategoryList(categories);
    }

    @Override
    public void showCategoryListRetrieveError(int stringId) {
        view.showCategoryListRetrieveError(stringId);
    }

    @Override
    public void showCategoryListRetrieveError(String str) {
        view.showCategoryListRetrieveError(str);
    }
}
