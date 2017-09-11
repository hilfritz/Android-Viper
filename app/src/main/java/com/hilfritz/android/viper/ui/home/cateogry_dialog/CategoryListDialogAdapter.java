package com.hilfritz.android.viper.ui.home.cateogry_dialog;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.ui.products.list.view.adapter.ProductListAdapter;

import java.util.ArrayList;

/**
 * Created by home on 9/11/2017.
 */

public class CategoryListDialogAdapter extends RecyclerView.Adapter<CategoryListDialogAdapter.ViewHolder>{

    Callback callback;


    public CategoryListDialogAdapter(Callback callback) {
        this.callback = callback;
    }

    public interface Callback{
        ArrayList<Category> getList();
        void onCategoryClick(Category category);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.category_list_dialog_item, parent, false);
        CategoryListDialogAdapter.ViewHolder vh = new CategoryListDialogAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category category = callback.getList().get(position);
        holder.name.setText(category.getName());



        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onCategoryClick(category);
            }
        });

        if (position%2==0){
            holder.root.setBackgroundResource(R.drawable.list_item_selector1);
        }else{
            holder.root.setBackgroundResource(R.drawable.list_item_selector1_darker);
        }
    }

    @Override
    public int getItemCount() {
        return callback.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        View root;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.text);
            root = itemView.findViewById(R.id.root);
        }
    }
}
