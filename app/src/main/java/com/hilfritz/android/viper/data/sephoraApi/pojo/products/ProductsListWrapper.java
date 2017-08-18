
package com.hilfritz.android.viper.data.sephoraApi.pojo.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsListWrapper {

    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}
