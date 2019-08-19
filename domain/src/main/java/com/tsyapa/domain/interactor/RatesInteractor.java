package com.tsyapa.domain.interactor;

import com.tsyapa.domain.executor.PostExecutionThread;
import com.tsyapa.domain.executor.ThreadExecutor;
import com.tsyapa.domain.interactor.base.BaseInteractor;
import com.tsyapa.domain.repository.RatesRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class RatesInteractor extends BaseInteractor {

    private RatesRepository ratesRepository;

    @Inject
    RatesInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
            RatesRepository ratesRepository) {
        super(threadExecutor, postExecutionThread);
        this.ratesRepository = ratesRepository;
    }

    public void updateRates(String baseCurrency, DisposableObserver<Map<String, Double>> observer){
        execute(ratesRepository.updateRates(baseCurrency), observer);
    }
}