package com.tsyapa.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {

    Scheduler provideScheduler();
}