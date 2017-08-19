
package com.hilfritz.android.viper.data.sephoraApi.pojo.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("products_count")
    @Expose
    private Integer productsCount;

    public Category(String name, Integer productsCount) {
        this.name = name;
        this.productsCount = productsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

}
