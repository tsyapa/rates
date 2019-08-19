package com.tsyapa.presentation.di.module.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tsyapa.presentation.tools.ViewModelFactory;
import com.tsyapa.presentation.ui.screens.rates.RatesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RatesViewModel.class)
    abstract ViewModel provideRatesActivityViewModel(RatesViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}