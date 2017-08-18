
package com.hilfritz.android.viper.data.sephoraApi.pojo.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoriesWrapper {

    @SerializedName("categories")
    @Expose
    private ArrayList<Category> categories = null;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}
