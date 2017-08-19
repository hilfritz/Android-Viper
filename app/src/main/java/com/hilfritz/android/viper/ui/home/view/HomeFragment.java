package com.hilfritz.android.viper.ui.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.base.BaseFragment;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.navigation.Router;
import com.hilfritz.android.viper.ui.home.HomePresenter;
import com.hilfritz.android.viper.ui.home.view.adapters.CartListAdapter;
import com.hilfritz.android.viper.ui.home.view.adapters.CategoryListAdapter;
import com.hilfritz.android.viper.ui.loading.FullscreenLoadingDialog;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends BaseFragment implements HomeView{
    private static final String TAG = "HomeFragment";
    @BindView(R.id.category_list)
    RecyclerView categoryList;

    @BindView(R.id.cart_list)
    RecyclerView cartList;


    @BindView(R.id.swipe_reveal_layout)
    SwipeRevealLayout swipeRevealLayout;

    CategoryListAdapter categoryListAdapter;
    CartListAdapter cartListAdapter;


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

    public void initializeLists(){
        //initialize views here
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        categoryList.setLayoutManager(llm);

        LinearLayoutManager llm2= new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        cartList.setLayoutManager(llm2);

        categoryListAdapter = new CategoryListAdapter(presenter, getActivity());
        cartListAdapter = new CartListAdapter(presenter, getActivity());

        cartList.setAdapter(cartListAdapter);
        categoryList.setAdapter(categoryListAdapter);
        cartListAdapter.notifyDataSetChanged();
        categoryListAdapter.notifyDataSetChanged();
        Log.d(TAG, "initializeLists: ");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(this, threadProvider, sephoraProductRepository);
        initializeLists();
        presenter.populate();
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading: ");
        //FullscreenLoadingDialog.showLoading(getFragmentManager());
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hideLoading: ");
        FullscreenLoadingDialog.hideLoading();
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
        Log.d(TAG, "showCategoryList: ");
        swipeRevealLayout.close(false);
        categoryListAdapter.notifyDataSetChanged();
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
        swipeRevealLayout.open(true);
        cartListAdapter.notifyDataSetChanged();
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
