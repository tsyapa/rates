package com.tsyapa.data.repository.base;

import com.tsyapa.data.entity.mapper.base.BaseMapper;

public class BaseRepository<Entity, Model, ApiDataSource> {

    protected final BaseMapper<Entity, Model> mapper;
    protected final ApiDataSource apiDataSource;

    protected BaseRepository(BaseMapper<Entity, Model> mapper, ApiDataSource apiDataSource) {
        this.mapper = mapper;
        this.apiDataSource = apiDataSource;
    }
}