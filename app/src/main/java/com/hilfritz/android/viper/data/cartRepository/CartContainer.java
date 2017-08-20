package com.hilfritz.android.viper.data.cartRepository;

import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 20/8/17.
 */

public class CartContainer {
    ArrayList<Product> products = new ArrayList<>();

    public CartContainer(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
