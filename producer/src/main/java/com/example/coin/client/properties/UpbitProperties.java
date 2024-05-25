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
    public URI marketCodeRequestUri() {
        return baseUriBuilder()
                .path(marketCodeUrl)
                .build().toUri();
    }

    public URI candlesMinutesRequestUri(String marketCode) {
        return baseUriBuilder()
                .path(candlesMinutesUrl)
                .queryParam("market", marketCode)
                .build().toUri();
    }

    private UriComponentsBuilder baseUriBuilder() {
        return UriComponentsBuilder.fromHttpUrl(baseUrl);
    }
}
