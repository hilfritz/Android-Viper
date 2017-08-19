package com.hilfritz.android.viper.ui.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.base.BaseFragment;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.navigation.Router;
import com.hilfritz.android.viper.ui.home.HomePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends BaseFragment implements HomeView{
    View view;
    @Inject
    HomePresenter presenter;
    @Inject
    ThreadProvider threadProvider;
    @Inject
    Router router;
    @Inject
    SephoraProductRepository sephoraProductRepository;

    public HomeFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(this, threadProvider, sephoraProductRepository);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onCategoryClick(String categoryName,int totalProductsInCategory) {
        presenter.openCategoryProductList(categoryName, totalProductsInCategory);
    }

    @Override
    public void openCategoryProductsPage(String categoryId,int totalProductsInCategory) {
        router.openProductLists(getActivity(), categoryId, totalProductsInCategory);
    }

    @Override
    public void showError(String str) {

    }


    @Override
    public void showCategoryList(ArrayList<Category> categories) {
        //// TODO: 19/8/17 show the category list in recyclerview

    }

    @Override
    public void showCategoryListRetrieveError(int stringId) {

        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCategoryListRetrieveError(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSavedCartList(ArrayList<Product> products) {
        //// TODO: 19/8/17 show the cart items in recyclerview
    }

    @Override
    public void showCartRetrieveError(int stringId) {
        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCartRetrieveError(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }
}