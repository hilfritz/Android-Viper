package com.hilfritz.android.viper.data.cartRepository;

import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface CartManager {
    ArrayList<Product> getProductsInCart();
    Product getProductById(long id);
    Product removeProductById(long id);
    Product insertProductToCart(Product product);
    int getCartSize();
    void clear();
    void save();
}
