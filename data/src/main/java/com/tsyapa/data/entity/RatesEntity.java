package com.tsyapa.data.entity;

import java.util.Map;

public class RatesEntity {

    private Map<String, Double> rates;

    public void setRates(Map<String, Double> rates) { this.rates = rates; }

    public Map<String, Double> getRates() { return rates; }
}