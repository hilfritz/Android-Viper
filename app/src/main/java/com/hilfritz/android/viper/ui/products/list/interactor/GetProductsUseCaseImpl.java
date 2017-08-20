package com.hilfritz.android.viper.ui.products.list.interactor;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.interactor.AbstractBaseInteractor;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductsListWrapper;

import java.util.ArrayList;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class GetProductsUseCaseImpl extends AbstractBaseInteractor implements GetProductsUseCase {
    public static final int INITIAL_PAGE =1;
    int currentPage = INITIAL_PAGE;
    String categoryName = "";
    int totalProductsInCategory = 0;
    GetProductsUseCase.Callback presenterCallBack;
    SephoraProductRepository sephoraProductRepository;

    int loadedCounter = 0;

    public GetProductsUseCaseImpl(Scheduler ioThread,
                                  Scheduler mainThread,
                                  SephoraProductRepository sephoraProductRepository,
                                  GetProductsUseCase.Callback presenterCallBack,
                                  String categoryName,
                                  int totalProductsInCategory
                                  ) {
        super(ioThread, mainThread);
        this.sephoraProductRepository = sephoraProductRepository;
        this.presenterCallBack = presenterCallBack;
        this.categoryName = categoryName;
        this.totalProductsInCategory = totalProductsInCategory;
        resetPage();
    }

    @Override
    public void run() {
        sephoraProductRepository.getProductsByPageAndCategory(getCurrentPage(), getCategoryName())
                .observeOn(getMainThread())
                .subscribeOn(getIoThread())
                .subscribe(new Subscriber<ProductsListWrapper>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getLocalizedMessage();
                        presenterCallBack.showProductListRetrieveError(message);
                        unsubscribe();
                    }

                    @Override
                    public void onNext(ProductsListWrapper productsListWrapper) {
                        if (productsListWrapper!=null
                                && productsListWrapper.getProducts()!=null
                                && productsListWrapper.getProducts().size()>0){
                            presenterCallBack.showProductList(productsListWrapper.getProducts());
                            incrementPage();
                            incrementLoadedCounterBy(productsListWrapper.getProducts().size());

                        }else {
                            //empty list
                            if (loadedCounter==totalProductsInCategory){
                                presenterCallBack.showProductList(new ArrayList<Product>());
                            }else{
                                presenterCallBack.showProductListRetrieveError(R.string.something_went_wrong);
                            }

                        }
                    }
                });
    }

    @Override
    public void incrementPage() {
        currentPage++;
    }

    @Override
    public void resetPage() {
        currentPage = INITIAL_PAGE;
        loadedCounter = 0;
    }

    @Override
    public void incrementLoadedCounterBy(int size) {
        loadedCounter+=size;
    }

    @Override
    public int getLoadedProductsCount() {
        return loadedCounter;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
