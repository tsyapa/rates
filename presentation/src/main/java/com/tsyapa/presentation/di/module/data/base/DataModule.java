package com.tsyapa.presentation.di.module.data.base;

import com.tsyapa.data.api.NetworkModule;
import com.tsyapa.data.executor.JobExecutor;
import com.tsyapa.domain.executor.PostExecutionThread;
import com.tsyapa.domain.executor.ThreadExecutor;
import com.tsyapa.presentation.di.module.data.RatesDataModule;
import com.tsyapa.presentation.executor.UiExecutionThread;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module(includes = {NetworkModule.class, RatesDataModule.class})
public abstract class DataModule {

    @Binds
    @Singleton
    abstract ThreadExecutor provideJobExecutor(JobExecutor jobExecutor);

    @Binds
    @Singleton
    abstract PostExecutionThread providePostExecutionThread(UiExecutionThread uiExecutionThread);
}