package com.hilfritz.android.viper.data.cartRepository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.util.SharedPrefUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 * see: https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
 */

public class CartManagerImpl implements CartManager{
    ArrayList<Product> productsInCart = new ArrayList<>();
    Gson gson = new Gson();
    private static final String CART_ITEMS_TAG = "cartItems";
    MyApplication myApplication;

    public CartManagerImpl() {
    }

    public CartManagerImpl(MyApplication myApplication) {
        loadSavedData(myApplication);
        this.myApplication = myApplication;

    }



    public Object fromJson(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

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

    @Override
    public void save() {
        final String s = gson.toJson(getProductsInCart());
        SharedPrefUtil.updatePref(CART_ITEMS_TAG, s, myApplication);
    }

    public void loadSavedData(MyApplication myApplication){
        String savedPreferenceJson = SharedPrefUtil.getPrefValue(CART_ITEMS_TAG, myApplication);
        ArrayList<Product> list=new ArrayList<>();
        try {
            list = (ArrayList<Product>) fromJson(savedPreferenceJson,
                    new TypeToken<ArrayList<Product>>() {
                    }.getType());
        }catch (Exception e){

        }
        if (list!=null && list.isEmpty()==false){
            productsInCart.addAll(list);
        }
    }
}
