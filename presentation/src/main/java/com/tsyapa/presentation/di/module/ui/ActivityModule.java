package com.tsyapa.presentation.di.module.ui;

import com.tsyapa.presentation.di.scope.ActivityScope;
import com.tsyapa.presentation.ui.screens.rates.RatesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract RatesActivity contributeRatesActivity();
}