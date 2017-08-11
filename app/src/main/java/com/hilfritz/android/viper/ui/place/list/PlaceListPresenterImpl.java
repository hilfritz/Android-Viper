package com.hilfritz.android.viper.ui.place.list;

import com.hilfritz.android.viper.ui.place.list.view.PlaceListView;

/**
 * Created by home on 8/6/2017.
 */

public class PlaceListPresenterImpl implements PlacesListPresenter {
    PlaceListView view;


    public PlaceListPresenterImpl() {

    }

    @Override
    public void init(PlaceListView view) {
        this.view = view;
        if (view.isNewlyCreatedPresenter()) {
            //INITIALIZE THE VIEWS
            //INITIALIZE THE ADAPTERS
            //INITIALIZE EVERYTHING

        }
    }

    @Override
    public void populate() {
        if (view.isNewlyCreatedPresenter()){
            view.showFullscreenMessage("newly created");
        }else {
            view.showFullscreenMessage("configuration change creation");
        }
    }
}
