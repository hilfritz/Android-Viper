package com.hilfritz.android.viper.ui.products.list.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.products.list.ProductListPresenter;
import com.hilfritz.android.viper.util.FrescoUtil;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{
    Context context;
    ProductListPresenter presenter;

    public ProductListAdapter(Context context, ProductListPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.list_item_product2, parent, false);
        ProductListAdapter.ViewHolder vh = new ProductListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = presenter.getList().get(position);
        holder.name.setText(product.getName());
        holder.price.setText(context.getString(R.string.price_holder)+product.getPrice());
        if (product.getUnderSale()){
            holder.sale.setText(R.string.on_sale);
        }else{
            holder.sale.setText("");
        }

        int width = context.getResources().getDimensionPixelSize(R.dimen.product_photo_width_height);
        try {
            FrescoUtil.loadImage(
                    Uri.parse(product.getImgUrl()),
                    holder.image,
                    context,
                    width,
                    width
            );
        }catch (Exception e){
            holder.image.setImageURI(FrescoUtil.getUriFromDrawableId(R.drawable.image_not_available));
        }
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openProductDetail(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView sale;
        SimpleDraweeView image;
        View root;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            price = (TextView)itemView.findViewById(R.id.price);
            sale = (TextView)itemView.findViewById(R.id.sale);
            image = (SimpleDraweeView)itemView.findViewById(R.id.image);
            root = itemView.findViewById(R.id.root);
        }
    }
}
