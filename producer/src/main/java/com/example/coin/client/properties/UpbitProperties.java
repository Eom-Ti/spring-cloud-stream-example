package com.example.coin.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@ConfigurationProperties(value = "coin.upbit.api")
public record UpbitProperties(
        String baseUrl,
        String marketCodeUrl,
        String candlesMinutesUrl
) {
    public String marketCodeRequestUrl() {
        return baseUrl + marketCodeUrl;
    }

    public URI candlesMinutesRequestUrI(String marketCode) {
        return UriComponentsBuilder.fromPath(baseUrl + candlesMinutesUrl)
                .queryParam("market", marketCode)
                .build().toUri();
    }
}
