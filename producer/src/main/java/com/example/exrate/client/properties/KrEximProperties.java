package com.example.exrate.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "exrate.api")
public record KrEximProperties(
        String baseUrl,
        String exrateUrl,
        String authKey
) {

    public String getRequestExrateUrl() {
        return baseUrl() + exrateUrl();
    }
}
