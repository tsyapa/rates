package com.tsyapa.data.entity.mapper;

import com.tsyapa.data.entity.RatesEntity;
import com.tsyapa.data.entity.mapper.base.BaseMapper;

import java.util.Map;

import javax.inject.Inject;

public class RatesEntityDataMapper extends BaseMapper<RatesEntity, Map<String, Double>> {

    @Inject
    RatesEntityDataMapper(){}

    @Override
    public Map<String, Double> transform(RatesEntity ratesEntity) { return ratesEntity.getRates(); }

    @Override
    public RatesEntity retransform(Map<String, Double> stringDoubleMap) {
        RatesEntity ratesEntity = new RatesEntity();
        ratesEntity.setRates(stringDoubleMap);
        return ratesEntity;
    }
}