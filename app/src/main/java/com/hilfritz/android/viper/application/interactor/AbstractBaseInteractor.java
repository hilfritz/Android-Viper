package com.hilfritz.android.viper.application.interactor;

import com.hilfritz.android.viper.data.sephoraApi.SephoraProductRepository;

import rx.Scheduler;

/**
 * Created by home on 8/6/2017.
 */

public abstract class AbstractBaseInteractor implements BaseInteractor {
    Scheduler ioThread;
    Scheduler mainThread;
    SephoraProductRepository sephoraProductRepository;

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

    @Override
    public abstract void run();

    public SephoraProductRepository getSephoraProductRepository() {
        return sephoraProductRepository;
    }

    public void setSephoraProductRepository(SephoraProductRepository sephoraProductRepository) {
        this.sephoraProductRepository = sephoraProductRepository;
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

