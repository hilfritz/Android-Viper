package com.hilfritz.android.viper.ui.home.interactor;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.interactor.AbstractBaseInteractor;
import com.hilfritz.android.viper.data.cartRepository.CartManager;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import java.util.ArrayList;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class RetrieveSavedCartItemsUseCaseImpl extends AbstractBaseInteractor implements RetrieveSavedCartItemsUseCase {
    CartManager cartManager;
    RetrieveSavedCartItemsUseCase.Callback presenterCallback;

    public RetrieveSavedCartItemsUseCaseImpl(Scheduler ioThread, Scheduler mainThread, CartManager cartManager, RetrieveSavedCartItemsUseCase.Callback presenterCallback) {
        super(ioThread, mainThread);
        this.cartManager = cartManager;
        this.presenterCallback = presenterCallback;
    }

    @Override
    public void run() {
        Observable.just(cartManager.getProductsInCart())
                .observeOn(getMainThread())
                .subscribeOn(getIoThread())
                .subscribe(new Subscriber<ArrayList<Product>>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getLocalizedMessage();
                        presenterCallback.showCartRetrieveError(message);
                        unsubscribe();
                    }

                    @Override
                    public void onNext(ArrayList<Product> products) {
                        if (products!=null &&
                                products.size() > 0){
                            presenterCallback.showSavedCartList(products);
                        }else {
                            presenterCallback.showCartRetrieveError(R.string.empty_cart);
                        }
                    }
                });
    }
}
