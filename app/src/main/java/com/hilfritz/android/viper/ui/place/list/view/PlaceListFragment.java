package com.hilfritz.android.viper.ui.place.list.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hilfritz.android.viper.R;
import com.hilfritz.android.viper.application.MyApplication;
import com.hilfritz.android.viper.application.base.BaseFragment;
import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.ui.place.list.PlacesListPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceListFragment extends BaseFragment implements PlaceListView {

    View view;

    @Inject
    PlacesListPresenter presenter;

    @Inject
    ThreadProvider thread;

    public PlaceListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_place_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(this, thread);
        presenter.populate();
    }

    @Override
    public void showList() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFullscreenMessage(String str) {

    }

    @Override
    public boolean isNewlyCreatedPresenter() {
        return getActivity().isChangingConfigurations()==false;
    }
}
