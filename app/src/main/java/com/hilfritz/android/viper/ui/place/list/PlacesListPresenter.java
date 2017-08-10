package com.hilfritz.android.viper.ui.place.list;

/**
 * Created by home on 8/6/2017.
 */

public interface PlacesListPresenter {
    public interface View{
        void showList();
        void showLoading();
        void hideLoading();
        void showFullscreenMessage(String str);
    }
}
