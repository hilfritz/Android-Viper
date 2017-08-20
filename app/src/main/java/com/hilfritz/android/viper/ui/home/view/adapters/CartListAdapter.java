package com.hilfritz.android.viper.ui.home.view.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.ui.home.HomePresenter;
import com.hilfritz.android.viper.util.FrescoUtil;

/**
 * Created by Hilfritz Camallere on 19/8/17.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    Context context;
    HomePresenter presenter;
    public CartListAdapter(HomePresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.list_item_product, parent, false);
        CartListAdapter.ViewHolder vh = new CartListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = presenter.getCartProductList().get(position);
        holder.name.setText(product.getName());
        String total = "$ "+product.getPrice();

        if (product.getNumberOfOrder()>0) {
            total = "$ "+product.getPrice()+" x ("+product.getNumberOfOrder()+") = $"+(product.getPrice()*product.getNumberOfOrder());
        }
        holder.price.setText(total);

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
        if (position%2==0) {
            holder.root.setBackground(ContextCompat.getDrawable(context, R.drawable.list_item_selector1));
        }else{
            holder.root.setBackground(ContextCompat.getDrawable(context, R.drawable.list_item_selector1_darker));
        }

    }

    @Override
    public int getItemCount() {
        return presenter.getCartListSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView total;
        SimpleDraweeView image;
        View root;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            price = (TextView)itemView.findViewById(R.id.price);
            total = (TextView)itemView.findViewById(R.id.total);
            image = (SimpleDraweeView)itemView.findViewById(R.id.image);

            root = itemView.findViewById(R.id.root);
        }
    }
}
