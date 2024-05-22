package com.example.coin.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "coin.upbit.api")
public record UpbitProperties(
        String baseUrl,
        String marketCodeUrl,
        String candlesMinutesUrl
) {
    public String marketCodeRequestUrl() {
        return baseUrl + marketCodeUrl;
    }

    public String clandlesMinutesRequestUrl() {
        return baseUrl + candlesMinutesUrl;
    }
}
