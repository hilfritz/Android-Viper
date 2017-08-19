package com.hilfritz.android.viper.ui.home;

import com.hilfritz.AndroidTest;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.CategoriesWrapper;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.ui.home.view.HomeView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;
import rx.schedulers.Schedulers;

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
public class HomePresenterTest extends AndroidTest{
    HomeView view;
    SephoraProductRepository sephoraProductRepository;
    ThreadProvider threadProvider;
    HomePresenter presenter;

    ArrayList<Category> sampleCategory =
            new ArrayList<>(Arrays.asList(new Category("lipstick", 100), new Category("eyeliners",200), new Category("powder", 150)));
    ArrayList<Category> emptyCategoryList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        view = mock(HomeView.class);
        sephoraProductRepository =mock(SephoraProductRepository.class);
        threadProvider = mock (ThreadProvider.class);

        when(threadProvider.getIoThread()).thenReturn(Schedulers.trampoline());
        when(threadProvider.getMainThread()).thenReturn(Schedulers.trampoline());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void successWhenLoadingCategoriesTest(){
        //init
        when(sephoraProductRepository.getCategories()).thenReturn(Observable.just(new CategoriesWrapper(sampleCategory)));
        presenter = new HomePresenterImpl();
        presenter.init(view, threadProvider, sephoraProductRepository);

        //act
        presenter.populate();

        //assert
        verify(view, atLeastOnce()).showLoading();
        verify(view, atLeastOnce()).showCategoryList(sampleCategory);
        verify(view, atLeastOnce()).hideLoading();
    }
    @Test
    public void loadingEmptyCategoriesTest(){
        //init
        when(sephoraProductRepository.getCategories()).thenReturn(Observable.just(new CategoriesWrapper(emptyCategoryList)));
        presenter = new HomePresenterImpl();
        presenter.init(view, threadProvider, sephoraProductRepository);

        //act
        presenter.populate();

        //assert
        verify(view, atLeastOnce()).showLoading();
        verify(view, atLeastOnce()).showCategoryListRetrieveError(anyInt());
        verify(view, never()).showCategoryListRetrieveError(anyString());
        verify(view, atLeastOnce()).hideLoading();
    }
    @Test
    public void exceptionWhenLoadingCategoriesTest(){
        //init
        Exception damnException = new Exception("damn!");
        presenter = mock(HomePresenter.class);
        sephoraProductRepository = mock(SephoraProductRepository.class);
        when(sephoraProductRepository.getCategories()).thenReturn(Observable.<CategoriesWrapper>error(damnException));

        presenter = new HomePresenterImpl();
        presenter.init(view, threadProvider, sephoraProductRepository);

        //act
        presenter.populate();

        //assert
        verify(view, atLeastOnce()).showLoading();
        verify(view, atLeastOnce()).showCategoryListRetrieveError(anyString());
        verify(view, never()).showCategoryListRetrieveError(anyInt());
        verify(view, atLeastOnce()).hideLoading();

    }

    @Test
    public void openCategoryProductsOnCategoriesClickTest(){
        //init
        when(sephoraProductRepository.getCategories()).thenReturn(Observable.just(new CategoriesWrapper(sampleCategory)));
        presenter = new HomePresenterImpl();
        presenter.init(view, threadProvider, sephoraProductRepository);
        String categoryId = "lipstick";
        int categoryProductCount = 1234;

        //act
        presenter.populate();
        //invoke the click on the category
        //view.onCategoryClick(categoryId);
        presenter.openCategoryProductList(categoryId, categoryProductCount);

        //assert
        verify(view, atLeastOnce()).openCategoryProductsPage(categoryId, categoryProductCount);
    }

}