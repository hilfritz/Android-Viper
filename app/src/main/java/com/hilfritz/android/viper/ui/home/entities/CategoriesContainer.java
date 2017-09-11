package com.hilfritz.android.viper.ui.home.entities;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;

import java.util.ArrayList;

/**
 * Created by home on 9/11/2017.
 */

public class CategoriesContainer {
    ArrayList<Category> categoryList = new ArrayList<>();

    public CategoriesContainer(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
