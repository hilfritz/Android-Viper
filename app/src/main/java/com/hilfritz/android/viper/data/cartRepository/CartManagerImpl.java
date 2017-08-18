package com.hilfritz.android.viper.data.cartRepository;

import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public class CartManagerImpl implements CartManager{
    ArrayList<Product> productsInCart = new ArrayList<>();
    @Override
    public ArrayList<Product> getProductsInCart() {
        return productsInCart;
    }

    @Override
    public Product getProductById(long id) {
        int size = getCartSize();
        if (size > 0){
            for (int i = 0; i < size; i++) {
                final Product product = getProductsInCart().get(i);
                boolean isSameId = product.getId()==id;
                if (isSameId){
                    return product;
                }
            }
        }
        return null;
    }

    @Override
    public Product removeProductById(long id) {
        int size = getCartSize();
        Product toRemove = null;
        if (size > 0){
            for (int i = 0; i < size; i++) {
                toRemove = getProductsInCart().get(i);
                boolean isSameId = toRemove.getId()==id;
                if (isSameId){
                    break;
                }else{
                    toRemove = null;
                }
            }
        }
        if (toRemove!=null){
            //REMOVE FROM LIST
            getProductsInCart().remove(toRemove);
        }
        return toRemove;
    }

    @Override
    public Product insertProductToCart(Product product) {
        int size = getCartSize();
        Product addedProduct = null;
        boolean existing = false;
        if (size > 0){
            for (int i = 0; i < size; i++) {
                Product productTemp = getProductsInCart().get(i);
                existing = product.getId()==productTemp.getId();
            }
        }
        if (existing){
            addedProduct = null;
        }else{
            getProductsInCart().add(product);
            addedProduct = product;
        }
        return addedProduct;
    }

    @Override
    public int getCartSize() {
        return productsInCart.size();
    }

    @Override
    public void clear() {
        productsInCart.clear();
    }

}
