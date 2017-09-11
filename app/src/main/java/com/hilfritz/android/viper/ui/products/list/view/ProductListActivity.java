package com.hilfritz.android.viper.ui.products.list.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.data.eventbus.DialogEvent;
import com.hilfritz.android.viper.navigation.RouterImpl;
import com.hilfritz.android.viper.ui.home.cateogry_dialog.CategoryListDialogFragment;
import com.hilfritz.android.viper.ui.home.entities.CategoriesContainer;


public class ProductListActivity extends AppCompatActivity {
    boolean hasCategories = false;
    CategoriesContainer categoriesContainerFromIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        changeToolbarTitle(getIntent().getStringExtra(RouterImpl.EXTRA_CATEGORY_NAME));

        categoriesContainerFromIntent = RouterImpl.getCategoriesContainerFromIntent(getIntent());
        if (categoriesContainerFromIntent!=null){
            //create the filter
            hasCategories = true;

        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (hasCategories) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_product_list, menu);
            return true;
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //respond to menu item selection
            case R.id.action_show_categories:
                CategoryListDialogFragment.newInstance(categoriesContainerFromIntent).show(getSupportFragmentManager(), "dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeToolbarTitle(String str){
        getSupportActionBar().setTitle(str);
    }

}
