package com.tsyapa.domain.repository;

import java.util.Map;

import io.reactivex.Observable;

public interface RatesRepository {

    Observable<Map<String, Double>> updateRates(String baseCurrency);
}