package com.hilfritz.android.viper.ui.products.detail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.base.BaseFragment;

import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProductDetailFragment extends BaseFragment {
    View view;

    public ProductDetailFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
