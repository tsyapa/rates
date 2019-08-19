package com.tsyapa.presentation.di.module.data;

import com.tsyapa.data.repository.RatesRepositoryImpl;
import com.tsyapa.domain.repository.RatesRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RatesDataModule {

    @Binds
    abstract RatesRepository provideAuthRepository(RatesRepositoryImpl ratesRepository);
}