package com.hilfritz.android.viper.ui.place.list;

import com.hilfritz.android.viper.ui.place.list.view.PlaceListView;

/**
 * Created by home on 8/6/2017.
 */


public interface PlacesListPresenter<V extends PlaceListView> {

    void init(V view);
    void populate();

}
