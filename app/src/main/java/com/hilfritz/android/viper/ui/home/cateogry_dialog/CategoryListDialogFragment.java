package com.hilfritz.android.viper.ui.home.cateogry_dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.data.eventbus.DialogEvent;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.navigation.RouterImpl;
import com.hilfritz.android.viper.ui.home.entities.CategoriesContainer;

import java.util.ArrayList;

/**
 * Created by home on 9/11/2017.
 */

public class CategoryListDialogFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    Gson gson = new Gson();

    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static CategoryListDialogFragment newInstance(CategoriesContainer container) {
        CategoryListDialogFragment f = new CategoryListDialogFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String categoriesStr = gson.toJson(container);
        args.putString(RouterImpl.EXTRA_CATEGORY_LIST, categoriesStr);
        f.setArguments(args);
        return f;
    }

    // this method create view for your Dialog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate layout with recycler view
        View v = inflater.inflate(R.layout.category_list_dialog, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //setadapter
        String categoriesListStr = getArguments().getString(RouterImpl.EXTRA_CATEGORY_LIST, null);
        final CategoriesContainer categoryContainer = gson.fromJson(categoriesListStr, CategoriesContainer.class);

        CategoryListDialogAdapter adapter = new CategoryListDialogAdapter(new CategoryListDialogAdapter.Callback() {
            @Override
            public ArrayList<Category> getList() {
                return categoryContainer.getCategoryList();
            }

            @Override
            public void onCategoryClick(Category category) {
                DialogEvent.fireEvent(DialogEvent.CATEGORY_CLICK, category);
                dismiss();
            }
        });
        mRecyclerView.setAdapter(adapter);
        //get your recycler view and populate it.
        adapter.notifyDataSetChanged();
        return v;
    }
}