package com.hilfritz.android.viper.ui.place.list;

/**
 * Created by home on 8/6/2017.
 */

public class PlaceListPresenterImpl<PlaceListView> implements PlacesListPresenter {

    PlaceListView view;
    public PlaceListPresenterImpl(PlaceListView view) {
        this.view = view;
    }

}
