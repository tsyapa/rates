package com.tsyapa.presentation.ui.screens.rates;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tsyapa.domain.interactor.RatesInteractor;
import com.tsyapa.presentation.tools.Resource;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

import static com.tsyapa.presentation.Constants.DEFAULT_AMOUNT;
import static com.tsyapa.presentation.Constants.DEFAULT_BASE_CURRENCY;

public class RatesViewModel extends ViewModel {

    private RatesInteractor ratesInteractor;
    private MutableLiveData<Resource<Map<String, Double>>> liveDataGetRates = new MutableLiveData<>();

    private String currency = DEFAULT_BASE_CURRENCY;
    private double amount = DEFAULT_AMOUNT;

    @Inject
    RatesViewModel(RatesInteractor ratesInteractor) { this.ratesInteractor = ratesInteractor; }

    @Override
    protected void onCleared() {
        super.onCleared();
        ratesInteractor.dispose();
    }

    void getRates() {
        ratesInteractor.updateRates(currency, new DisposableObserver<Map<String, Double>>() {
            @Override
            public void onNext(Map<String, Double> rates) {
                liveDataGetRates.postValue(Resource.success(rates));
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        });
    }

    void onBaseAmountChanged(double amount) { this.amount = amount; }

    void onBaseCurrencyChanged(String currency) {
        ratesInteractor.dispose();
        this.currency = currency;
        getRates();
    }

    double getAmount() { return amount; }

    String getCurrency() { return currency; }

    MutableLiveData<Resource<Map<String, Double>>> getLiveDataGetRates() { return liveDataGetRates; }
}