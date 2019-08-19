package com.tsyapa.data.api.source;

import com.tsyapa.data.api.service.RatesService;
import com.tsyapa.data.api.source.base.BaseApiDataSource;
import com.tsyapa.data.entity.RatesEntity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.tsyapa.data.Constants.SECONDS_TO_UPDATE;

public class RatesApiDataSource extends BaseApiDataSource<RatesService> {

    @Inject
    RatesApiDataSource(RatesService service) { super(service); }

    public Observable<RatesEntity> updateRates(String baseCurrency){
        return Observable
                .interval(0, SECONDS_TO_UPDATE, TimeUnit.SECONDS)
                .flatMap(i -> getApiService().getRates(baseCurrency));
    }
}