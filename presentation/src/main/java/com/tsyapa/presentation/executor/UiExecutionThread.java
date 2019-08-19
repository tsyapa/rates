package com.tsyapa.presentation.executor;

import com.tsyapa.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UiExecutionThread implements PostExecutionThread {

    @Inject
    UiExecutionThread() { }

    @Override
    public Scheduler provideScheduler() {
        return AndroidSchedulers.mainThread();
    }
}