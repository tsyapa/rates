package com.tsyapa.data.api.source.base;

public abstract class BaseApiDataSource<ApiService> {

    private ApiService apiService;

    public BaseApiDataSource(final ApiService apiService) { this.apiService = apiService; }

    protected ApiService getApiService() { return apiService; }
}