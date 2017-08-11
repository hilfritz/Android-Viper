package com.hilfritz.android.viper.util.presenter_instance_checker;

import android.app.Activity;

/**
 * Created by Hilfritz Camallere on 11/8/17.
 */

public interface PresenterLifecycleManager {
    void init(Activity context);
    void onRetainNonConfigurationInstanceCalled();
    boolean isFromConfiguration();
    void isFinishing();
}
