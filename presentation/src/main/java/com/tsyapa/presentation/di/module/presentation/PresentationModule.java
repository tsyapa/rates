package com.tsyapa.presentation.di.module.presentation;

import android.content.Context;

import com.tsyapa.presentation.RatesApp;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PresentationModule {

    @Singleton
    @Binds
    abstract Context provideContext(RatesApp fishkaApp);
}