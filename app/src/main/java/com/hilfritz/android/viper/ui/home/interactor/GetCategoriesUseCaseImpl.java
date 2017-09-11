package com.hilfritz.android.viper.ui.home.interactor;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.interactor.AbstractBaseInteractor;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public class GetCategoriesUseCaseImpl extends AbstractBaseInteractor implements GetCategoriesUseCase {
    SephoraProductRepository sephoraProductRepository;
    GetCategoriesUseCase.Callback presenterCallBack;

    public GetCategoriesUseCaseImpl(Scheduler ioThread,
                                    Scheduler mainThread,
                                    SephoraProductRepository sephoraProductRepository,
                                    GetCategoriesUseCase.Callback presenterCallBack) {
        super(ioThread, mainThread);
        this.sephoraProductRepository = sephoraProductRepository;
        this.presenterCallBack = presenterCallBack;
    }

    @Override
    public void run() {
        sephoraProductRepository.getCategories()
                .observeOn(getMainThread())
                .subscribeOn(getIoThread())
                .subscribe(new Subscriber<CategoriesWrapper>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getLocalizedMessage();
                        presenterCallBack.showCategoryListRetrieveError(message);
                        unsubscribe();
                    }

                    @Override
                    public void onNext(CategoriesWrapper categoriesWrapper) {
                        if (categoriesWrapper!=null
                                && categoriesWrapper.getCategories()!=null
                                && categoriesWrapper.getCategories().size()>0){
                            presenterCallBack.showCategoryList(categoriesWrapper.getCategories());
                        }else{
                            //empty list
                            presenterCallBack.showCategoryListRetrieveError(R.string.no_categories);
                        }
                    }
                });
    }
}
