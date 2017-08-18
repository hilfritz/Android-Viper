package com.hilfritz.android.viper.ui.place.list;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.ui.place.list.view.PlaceListView;

/**
 * Created by home on 8/6/2017.
 */


public interface PlacesListPresenter {

    void init(PlaceListView view, ThreadProvider threadProvider);
    void populate();

}
