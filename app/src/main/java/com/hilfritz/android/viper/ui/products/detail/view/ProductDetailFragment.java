package com.hilfritz.android.viper.ui.products.detail.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.base.BaseFragment;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.cartRepository.CartManager;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;
import com.hilfritz.android.viper.navigation.Router;
import com.hilfritz.android.viper.navigation.RouterImpl;
import com.hilfritz.android.viper.ui.loading.FullscreenLoadingDialog;
import com.hilfritz.android.viper.ui.products.detail.ProductDetailPresenter;
import com.hilfritz.android.viper.util.FrescoUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProductDetailFragment extends BaseFragment implements ProductDetailView{
    View view;
    private static final String TAG = "ProductDetailFragment";
    @Inject
    ThreadProvider threadProvider;
    @Inject
    Router router;
    @Inject
    SephoraProductRepository sephoraProductRepository;
    @Inject
    CartManager cartManager;

    @BindView(R.id.image)
    SimpleDraweeView image;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.sale)
    TextView sale;

    @BindView(R.id.desc)
    TextView desc;

    @BindView(R.id.add_to_cart_btn)
    Button addToCartBtn;

    @BindView(R.id.remove_from_cart_btn)
    Button removeFromCartBtn;

    @Inject
    ProductDetailPresenter presenter;

    long productId = 0;

    public ProductDetailFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplication()).getAppComponent().inject(this);
        productId = getActivity().getIntent().getLongExtra(RouterImpl.EXTRA_PRODUCT_ID, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(this, threadProvider, sephoraProductRepository, cartManager, productId);
        presenter.populate();
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading: ");
        FullscreenLoadingDialog.showLoading(getChildFragmentManager());
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hideLoading: ");
        FullscreenLoadingDialog.hideLoading();
    }

    @Override
    public void showProductDetails(final Product product) {
        final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.product_detail_image_width_height);
        Uri uri = null;

        try {
            uri = Uri.parse(product.getImgUrl());
        }catch (Exception e){
            uri = FrescoUtil.getUriFromDrawableId(R.drawable.image_not_available);
        }
        if (uri!=null) {
            FrescoUtil.loadImage(
                    uri,
                    image,
                    getActivity(),
                    dimensionPixelSize,
                    dimensionPixelSize);
        }
        ((ProductDetailActivity)getActivity()).setTitleBarTitle(product.getName());
        name.setText(product.getName());
        price.setText(getString(R.string.price_holder)+product.getPrice());
        if (product.getUnderSale()){
            sale.setText(getString(R.string.on_sale));
        }else{
            sale.setText("");
        }

        desc.setText(product.getDescription());
        boolean isProductInCart = cartManager.getProductById(product.getId())!=null;
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(product);
            }
        });
        removeFromCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFromCart(product);
            }
        });
        if (isProductInCart){
            hideAddToCartButton();
        }else{
            showAddToCartButton();
        }

    }

    public void showAddToCartButton() {
        addToCartBtn.setVisibility(View.VISIBLE);
        removeFromCartBtn.setVisibility(View.GONE);
    }
    public void hideAddToCartButton() {
        addToCartBtn.setVisibility(View.GONE);
        removeFromCartBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(int strId) {
        Toast.makeText(getActivity(), getString(strId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetrieveProductSuccess(Product product) {
        showProductDetails(product);
    }

    @Override
    public void onRetrieveProductFail(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetrieveProductFail(int strId) {
        Toast.makeText(getActivity(), getString(strId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addToCart(Product product) {
        presenter.addToCart(product);

    }

    @Override
    public void removeFromCart(Product product) {
        presenter.removeFromCart(product);
    }

    @Override
    public void incrementCartCount(Product product) {
        presenter.incrementCartCount(product);
    }

    @Override
    public void decrementCartCount(Product product) {
        presenter.decrementCartCount(product);
    }
}
