package com.hilfritz.android.viper.ui.products.list.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.base.BaseFragment;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.navigation.Router;
import com.hilfritz.android.viper.navigation.RouterImpl;
import com.hilfritz.android.viper.ui.products.list.ProductListPresenter;
import com.hilfritz.android.viper.ui.products.list.view.adapter.ProductListAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListFragment extends BaseFragment implements ProductListView{
    private static final String TAG = "ProductListFragment";
    @BindView(R.id.list)
    XRecyclerView list;

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
    ProductListAdapter adapter;

    public ProductListFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplication()).getAppComponent().inject(this);
        categoryName = getActivity().getIntent().getStringExtra(RouterImpl.EXTRA_CATEGORY_NAME);
        totalProductsInCategory = getActivity().getIntent().getIntExtra(RouterImpl.EXTRA_CATEGORY_TOTAL_PRODUCT_COUNT, 0);
        Log.d(TAG, "onCreate: categoryName:"+categoryName+" totalProductsInCategory:"+totalProductsInCategory);

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

        //initialize lists
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);



        adapter = new ProductListAdapter(getActivity(), presenter);
        list.setAdapter(adapter);
        list.setPullRefreshEnabled(false);
        list.setLoadingMoreEnabled(true);
        list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        presenter.populate();
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
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProductList(ArrayList<Product> products) {
        adapter.notifyDataSetChanged();
        list.loadMoreComplete();
    }

    @Override
    public void showProductListRetrieveError(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        list.loadMoreComplete();
    }

    @Override
    public void showProductListRetrieveError(int stringId) {
        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_SHORT).show();
        list.loadMoreComplete();
    }

    @Override
    public void loadMoreFinish() {
        list.loadMoreComplete();
    }

    @Override
    public void loadMore() {
        Log.d(TAG, "loadMore: ");
        presenter.loadMore();
    }

}
