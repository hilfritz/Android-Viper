package com.hilfritz.android.viper.application.interactor;

import rx.Scheduler;

/**
 * Created by home on 8/6/2017.
 */

public abstract class AbstractBaseInteractor {
    Scheduler ioThread;
    Scheduler mainThread;


    protected boolean mIsCanceled;
    protected boolean mIsRunning;

    public AbstractBaseInteractor(Scheduler ioThread, Scheduler mainThread) {
        this.ioThread = ioThread;
        this.mainThread = mainThread;
    }

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public void onFinish() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void isCancelled() {

    }

    public void isRunning() {

    }


    public Scheduler getIoThread() {
        return ioThread;
    }

    public void setIoThread(Scheduler ioThread) {
        this.ioThread = ioThread;
    }

    public Scheduler getMainThread() {
        return mainThread;
    }

    public void setMainThread(Scheduler mainThread) {
        this.mainThread = mainThread;
    }
}

