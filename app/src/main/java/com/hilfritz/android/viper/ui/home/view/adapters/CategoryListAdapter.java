package com.hilfritz.android.viper.ui.home.view.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;
import com.hilfritz.android.viper.ui.home.HomePresenter;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    Context context;
    HomePresenter presenter;

    public CategoryListAdapter(HomePresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.list_item_category, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category category = presenter.getCategoryList().get(position);
        holder.title.setText(category.getName());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openCategoryProductList(category.getName(), category.getProductsCount());
            }
        });
        if (position%2==0) {
            holder.root.setBackground(ContextCompat.getDrawable(context, R.drawable.list_item_selector1));
        }else{
            holder.root.setBackground(ContextCompat.getDrawable(context, R.drawable.list_item_selector1_darker));
        }
    }

    @Override
    public int getItemCount() {
        return presenter.getCategoryListSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        View root;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            root = itemView.findViewById(R.id.root);
        }
    }
}
