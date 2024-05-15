package com.example.exrate.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "exrate.api")
public record KrEximProperties(
        String baseUrl,
        String exrateUrl,
        String authKey
) {

    public static final String AUTH_KEY = "authkey";
    public static final String SEARCH_DATE = "searchdate";
    public static final String DATA = "data";
    public static final String EXRATE_TYPE = "AP01";

    public String getRequestExrateUrl() {
        return baseUrl() + exrateUrl();
    }
}
