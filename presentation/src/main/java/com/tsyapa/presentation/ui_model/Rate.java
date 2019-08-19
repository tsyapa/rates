package com.tsyapa.presentation.ui_model;

public class Rate {

    private String currency;
    private double rate;
    private double amount;

    public Rate(String currency, double rate, double amount) {
        this.currency = currency;
        this.rate = rate;
        this.amount = amount;
    }

    public Rate(String currency, double rate) { this(currency, rate, 0); }

    public String getCurrency() { return currency; }

    public void setRate(double rate) { this.rate = rate; }

    public double getRate() { return rate; }

    public void setAmount(double amount) { this.amount = amount; }

    public double getAmount() { return amount; }
}