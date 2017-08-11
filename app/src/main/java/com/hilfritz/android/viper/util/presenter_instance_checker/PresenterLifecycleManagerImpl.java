package com.hilfritz.android.viper.util.presenter_instance_checker;

import android.app.Activity;

/**
 * Created by Hilfritz Camallere on 11/8/17.
 * Only available for Android 3.x (API Level 11+)
 * see https://stackoverflow.com/questions/7295804/is-that-possible-to-check-was-oncreate-called-because-of-orientation-change
 * see https://stackoverflow.com/questions/6716893/how-to-distinguish-whether-ondestroy-is-called-as-part-of-configuration-change?noredirect=1&lq=1
 *
 */

public class PresenterLifecycleManagerImpl implements PresenterLifecycleManager{
    Activity context;

    public PresenterLifecycleManagerImpl() {
    }

    @Override
    public void init(Activity context) {
        this.context = context;
    }

    @Override
    public void onRetainNonConfigurationInstanceCalled() {

    }

    @Override
    public boolean isFromConfiguration() {
        return context.isChangingConfigurations();
    }

    @Override
    public void isFinishing() {
    }
}
