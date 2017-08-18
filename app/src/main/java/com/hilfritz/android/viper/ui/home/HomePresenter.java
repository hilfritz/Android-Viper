package com.hilfritz.android.viper.ui.home;

import com.hilfritz.android.viper.application.thread.ThreadProvider;
import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;
import com.hilfritz.android.viper.ui.home.view.HomeView;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */

public interface HomePresenter {
    void init(HomeView view, ThreadProvider threadProvider, SephoraProductRepository sephoraProductRepository);
    void populate();
    void showError(String str);
    void showCartCount(String str);

}
