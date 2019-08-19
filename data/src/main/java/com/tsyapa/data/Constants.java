package com.tsyapa.data;

public interface Constants {

    int SECONDS_TO_UPDATE = 1;

    interface Api{
        String BASE_URL = "https://revolut.duckdns.org";

        interface Methods{
            String LATEST = "latest";
        }

        interface Params{
            String BASE = "base";
        }
    }
}