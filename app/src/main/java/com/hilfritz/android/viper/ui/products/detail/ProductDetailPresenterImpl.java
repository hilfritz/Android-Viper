package com.hilfritz.android.viper.ui.products.detail;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.cartRepository.CartManager;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.products.detail.interactor.GetProductDetailsUseCase;
import com.hilfritz.android.viper.ui.products.detail.interactor.GetProductDetailsUseCaseImpl;
import com.hilfritz.android.viper.ui.products.detail.view.ProductDetailView;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class ProductDetailPresenterImpl implements ProductDetailPresenter {
    ProductDetailView view;
    ThreadProvider threadProvider;
    SephoraProductRepository sephoraProductRepository;
    long productId = 0;
    CartManager cartManager;
    @Override
    public void init(ProductDetailView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository, CartManager cartManager, long productId) {
        this.view = view;
        this.threadProvider = threadProvider;
        this.sephoraProductRepository = sephoraProductRepository;
        this.productId = productId;
        this.cartManager = cartManager;

    }

    @Override
    public void populate() {
        view.showLoading();
        GetProductDetailsUseCase useCase = new GetProductDetailsUseCaseImpl(
                threadProvider.getIoThread(),
                threadProvider.getMainThread(),
                cartManager,
                sephoraProductRepository,
                productId,
                this
        );
        useCase.run();
        view.hideLoading();
    }
    @Override
    public void onRetrieveProductSuccess(Product product) {
        view.showProductDetails(product);
    }

    @Override
    public void onRetrieveProductFail(String str) {
        view.onRetrieveProductFail(str);
    }

    @Override
    public void onRetrieveProductFail(int strId) {
        view.onRetrieveProductFail(strId);
    }

    @Override
    public void addToCart(Product product) {
        Product insertedProduct = cartManager.insertProductToCart(product);
        if (insertedProduct!=null){
            view.showError(R.string.added);
            view.hideAddToCartButton();
        }else{
            view.showAddToCartButton();
            view.showError(R.string.something_went_wrong);
        }
    }

    @Override
    public void removeFromCart(Product product) {
        Product toRemove = cartManager.removeProductById(product.getId());
        if (toRemove!=null){
            view.showAddToCartButton();
            view.showError(R.string.removed);
        }else{
            view.hideAddToCartButton();
            view.showError(R.string.something_went_wrong);
        }
        //hide remove product from cart
    }

    @Override
    public void incrementCartCount(Product product) {
        final Product productToIncrement = cartManager.getProductById(product.getId());
        if (productToIncrement!=null){
            int previousCount = productToIncrement.getNumberOfOrder();
            productToIncrement.setNumberOfOrder(previousCount+1);
        }else{
            view.showError(R.string.something_went_wrong);
        }
    }

    @Override
    public void decrementCartCount(Product product) {
        final Product productToDecrementCount = cartManager.getProductById(product.getId());
        if (productToDecrementCount!=null){
            int previousCount = productToDecrementCount.getNumberOfOrder();
            if (previousCount>0) {
                productToDecrementCount.setNumberOfOrder(previousCount - 1);
            }
        }else{
            view.showError(R.string.something_went_wrong);
        }
    }





    @Override
    public void showError(String str) {

    }
}
