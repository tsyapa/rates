package com.tsyapa.data.repository;

import com.tsyapa.data.api.source.RatesApiDataSource;
import com.tsyapa.data.entity.RatesEntity;
import com.tsyapa.data.entity.mapper.RatesEntityDataMapper;
import com.tsyapa.data.repository.base.BaseRepository;
import com.tsyapa.domain.repository.RatesRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RatesRepositoryImpl extends BaseRepository<RatesEntity, Map<String, Double>, RatesApiDataSource>
        implements RatesRepository {

    @Inject
    RatesRepositoryImpl(RatesEntityDataMapper ratesEntityDataMapper, RatesApiDataSource ratesApiDataSource) {
        super(ratesEntityDataMapper, ratesApiDataSource);
    }

    @Override
    public Observable<Map<String, Double>> updateRates(String baseCurrency) {
        return apiDataSource.updateRates(baseCurrency).map(mapper::transform);
    }
}