package com.hilfritz.android.viper.ui.products.detail.interactor;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.interactor.AbstractBaseInteractor;
import com.hilfritz.android.viper.data.cartRepository.CartManager;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.ProductDetailsWrapper;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Hilfritz Camallere on 20/8/17.
 */

public class GetProductDetailsUseCaseImpl extends AbstractBaseInteractor implements GetProductDetailsUseCase{
    GetProductDetailsUseCase.Callback presenterCallback;
    CartManager cartManager;
    SephoraProductRepository sephoraProductRepository;
    long productId = 0;

    public GetProductDetailsUseCaseImpl(
            Scheduler ioThread,
            Scheduler mainThread,
            CartManager cartManager,
            SephoraProductRepository sephoraProductRepository,
            long productId,
            GetProductDetailsUseCase.Callback presenterCallback
            ) {
        super(ioThread, mainThread);
        this.sephoraProductRepository = sephoraProductRepository;
        this.cartManager = cartManager;
        this.productId = productId;
        this.presenterCallback = presenterCallback;
    }

    @Override
    public void run() {
        sephoraProductRepository.getProductById(productId)
                .observeOn(getMainThread())
                .subscribeOn(getIoThread())
                .subscribe(new Subscriber<ProductDetailsWrapper>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getLocalizedMessage();
                        presenterCallback.onRetrieveProductFail(message);
                        unsubscribe();
                    }

                    @Override
                    public void onNext(ProductDetailsWrapper productDetailsWrapper) {
                        if (productDetailsWrapper==null){
                            presenterCallback.onRetrieveProductFail(R.string.something_went_wrong);
                        }else{
                            presenterCallback.onRetrieveProductSuccess(productDetailsWrapper.getProduct());
                        }
                    }
                });
    }
}
