package com.hilfritz.android.viper.ui.products.list;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.products.list.interactor.GetProductsUseCase;
import com.hilfritz.android.viper.ui.products.list.interactor.GetProductsUseCaseImpl;
import com.hilfritz.android.viper.ui.products.list.view.ProductListView;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class ProductListPresenterImpl implements ProductListPresenter {
    ThreadProvider threadProvider;
    SephoraProductRepository sephoraProductRepository;
    ProductListView view;
    GetProductsUseCase getProductsUseCase;

    @Override
    public void init(ProductListView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository,String categoryName, int totalProductsInCategory) {
        this.threadProvider = threadProvider;
        this.sephoraProductRepository = sephoraProductRepository;
        this.view = view;
        getProductsUseCase = new GetProductsUseCaseImpl(
                threadProvider.getIoThread(),
                threadProvider.getMainThread(),
                sephoraProductRepository,
                this,
                categoryName,
                totalProductsInCategory
        );
    }

    @Override
    public void populate() {
        view.showLoading();
        getProductsUseCase.run();
        view.hideLoading();
    }

    @Override
    public void showError(String str) {
        view.showError(str);
    }

    @Override
    public void openProductDetail(Product product) {
        view.openProductDetailPage(product);
    }

    @Override
    public void showProductList(ArrayList<Product> products) {
        view.showProductList(products);
    }

    @Override
    public void showProductListRetrieveError(String str) {
        view.showProductListRetrieveError(str);
    }

    @Override
    public void showProductListRetrieveError(int stringId) {
        view.showProductListRetrieveError(stringId);
    }
}