package com.tsyapa.presentation.ui.screens.rates;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.tsyapa.presentation.R;
import com.tsyapa.presentation.ui.screens.base.BaseActivity;
import com.tsyapa.presentation.ui_model.Rate;

public class RatesActivity extends BaseActivity<RatesViewModel>
        implements RateAdapter.OnBaseAmountChanged {

    private RateAdapter rateAdapter = new RateAdapter(this);

    @Override
    protected int getContentView() { return R.layout.activity_main; }

    @Override
    protected Class<RatesViewModel> provideViewModelClass() { return RatesViewModel.class; }

    @Override
    protected void initUi() { }

    @Override
    protected void configure() {
        recyclerView.setAdapter(rateAdapter);
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        getViewModel().getLiveDataGetRates().observe(this, resource -> {
            if(resource.data!=null){
                rateAdapter.updateRates(resource.data,
                        new Rate(getViewModel().getCurrency(), 1, getViewModel().getAmount()));
            }
        });

        getViewModel().getRates();
    }

    @Override
    public void onBaseAmountChanged(double amount) { getViewModel().onBaseAmountChanged(amount); }

    @Override
    public void onBaseCurrencyChanged(String currency) { getViewModel().onBaseCurrencyChanged(currency); }
}