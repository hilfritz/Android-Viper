package com.hilfritz.android.viper.ui.place.list.view;

import com.hilfritz.android.viper.application.base.BaseView;

/**
 * Created by Hilfritz Camallere on 11/8/17.
 */

public interface PlaceListView extends BaseView {
    void showList();
    void showLoading();
    void hideLoading();
    void showFullscreenMessage(String str);
}
