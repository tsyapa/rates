package com.tsyapa.data.api.service;

import com.tsyapa.data.entity.RatesEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.tsyapa.data.Constants.Api.Methods.LATEST;
import static com.tsyapa.data.Constants.Api.Params.BASE;

public interface RatesService {

    @GET(LATEST)
    Observable<RatesEntity> getRates(@Query(BASE) String base);
}