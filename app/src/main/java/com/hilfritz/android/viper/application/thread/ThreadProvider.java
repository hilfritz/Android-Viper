package com.hilfritz.android.viper.application.thread;

import rx.Scheduler;

/**
 * Created by home on 8/6/2017.
 */

public interface ThreadProvider {
    Scheduler getIoThread();
    Scheduler getMainThread();
}
