package com.hilfritz.android.viper.ui.home.interactor;

import com.hilfritz.AndroidTest;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.ui.home.HomePresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */
public class GetCategoriesUseCaseTest extends AndroidTest{
    SephoraProductRepository sephoraProductRepository;
    HomePresenter presenter;

    ArrayList<Category> sampleCategory =
            new ArrayList<>(Arrays.asList(new Category("lipstick", 100), new Category("eyeliners",200), new Category("powder", 150)));
    ArrayList<Category> emptyCategoryList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        //MOCKS THE subscribeOn(Schedulers.io()) to use the same thread the test is being run on
        //Schedulers.trampoline() runs the test in the same thread used by the test
        RxJavaHooks.setOnIOScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return Schedulers.trampoline();
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        RxJavaHooks.reset();
    }
    @Test
    public void loadCategoriesSuccessTest(){
        //init
        presenter = mock(HomePresenter.class);
        sephoraProductRepository = mock(SephoraProductRepository.class);
        when(sephoraProductRepository.getCategories()).thenReturn(Observable.just(new CategoriesWrapper(sampleCategory)));

        GetCategoriesUseCase useCase = new GetCategoriesUseCaseImpl(
                Schedulers.trampoline(),
                Schedulers.trampoline(),
                sephoraProductRepository,
                presenter
                );

        //act
        useCase.run();

        //assert
        verify(presenter, atLeastOnce()).showCategoryList(sampleCategory);
        verify(presenter, never()).showError(anyString());
        verify(presenter, never()).showCategoryListRetrieveError(anyString());
        verify(presenter, never()).showCategoryListRetrieveError(anyInt());

    }
    @Test
    public void loadEmptyCategoriesTest(){
        //init
        presenter = mock(HomePresenter.class);
        sephoraProductRepository = mock(SephoraProductRepository.class);
        when(sephoraProductRepository.getCategories()).thenReturn(Observable.just(new CategoriesWrapper(emptyCategoryList)));

        GetCategoriesUseCase useCase = new GetCategoriesUseCaseImpl(
                Schedulers.trampoline(),
                Schedulers.trampoline(),
                sephoraProductRepository,
                presenter
        );

        //act
        useCase.run();

        //assert
        verify(presenter, never()).showCategoryList((ArrayList<Category>) any());
        verify(presenter, atLeastOnce()).showCategoryListRetrieveError(anyInt());
        verify(presenter, never()).showCategoryListRetrieveError(anyString());
    }

    @Test
    public void loadErrorCategoriesTest(){
        //init
        Exception damnException = new Exception("damn!");
        presenter = mock(HomePresenter.class);
        sephoraProductRepository = mock(SephoraProductRepository.class);
        when(sephoraProductRepository.getCategories()).thenReturn(Observable.<CategoriesWrapper>error(damnException));

        GetCategoriesUseCase useCase = new GetCategoriesUseCaseImpl(
                Schedulers.trampoline(),
                Schedulers.trampoline(),
                sephoraProductRepository,
                presenter
        );

        //act
        useCase.run();

        //assert
        verify(presenter, never()).showCategoryList((ArrayList<Category>) any());
        verify(presenter, never()).showCategoryListRetrieveError(anyInt());
        verify(presenter, atLeastOnce()).showCategoryListRetrieveError(anyString());
    }

}