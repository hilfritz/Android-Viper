package com.hilfritz.android.viper.ui.products.list.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.base.BaseFragment;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.navigation.Router;
import com.hilfritz.android.viper.navigation.RouterImpl;
import com.hilfritz.android.viper.ui.products.list.ProductListPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListFragment extends BaseFragment implements ProductListView{

    View view;
    @Inject
    ProductListPresenter presenter;
    @Inject
    ThreadProvider threadProvider;
    @Inject
    Router router;
    @Inject
    SephoraProductRepository sephoraProductRepository;
    String categoryName;
    int totalProductsInCategory =0;

    public ProductListFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplication()).getAppComponent().inject(this);
        categoryName = getActivity().getIntent().getStringExtra(RouterImpl.EXTRA_CATEGORY_NAME);
        totalProductsInCategory = getActivity().getIntent().getIntExtra(RouterImpl.EXTRA_CATEGORY_TOTAL_PRODUCT_COUNT, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(this, threadProvider, sephoraProductRepository, categoryName, totalProductsInCategory);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onProductClick(Product product) {
        presenter.openProductDetail(product);
    }

    @Override
    public void openProductDetailPage(Product product) {
        router.openProductDetails(getActivity(), product.getId());
    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void showProductList(ArrayList<Product> products) {

    }

    @Override
    public void showProductListRetrieveError(String str) {

    }

    @Override
    public void showProductListRetrieveError(int stringId) {

    }
}
