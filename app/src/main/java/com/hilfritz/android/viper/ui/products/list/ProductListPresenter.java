package com.hilfritz.android.viper.ui.products.list;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.products.list.view.ProductListView;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public interface ProductListPresenter{
    void init(ProductListView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository, String categoryName, int totalProductsInCategory);
    void populate();
    void showError(String str);
    void openProductDetail(Product product);
    ArrayList<Product> getList();


    void showProductList(ArrayList<Product> products);
    void showProductListRetrieveError(String str);
    void showProductListRetrieveError(int stringId);
    void loadMoreFinish();
    void loadMore();
}
