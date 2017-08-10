package com.hilfritz.android.viper.application.thread;

import rx.Scheduler;

/**
 * Created by home on 8/6/2017.
 */

public class ThreadProviderImpl implements ThreadProvider {
    Scheduler ioThread;
    Scheduler mainThread;

    public ThreadProviderImpl(Scheduler io, Scheduler scheduler) {
        this.ioThread = io;
        this.mainThread = scheduler;
    }


    @Override
    public Scheduler getIoThread() {
        return ioThread;
    }

    @Override
    public Scheduler getMainThread() {
        return mainThread;
    }
}
