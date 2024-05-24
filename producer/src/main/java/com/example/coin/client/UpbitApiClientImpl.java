package com.example.coin.client;

import com.example.coin.client.data.MarketCode;
import com.example.coin.client.properties.UpbitProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpbitApiClientImpl implements CoinApiClient {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final UpbitProperties upbitProperties;

    private List<MarketCode> marketCodes;

    @Override
    public String getCandlesByMinute(String marketCode) {
        Assert.hasText(marketCode, "marketCode must not be empty");

        URI requestUri = upbitProperties.candlesMinutesRequestUrI(marketCode);
        log.info("[UpbitApiClientImpl.getCandlesByMinute]requestUri: {}", requestUri.getPath());

        return restClient.get()
                .uri(requestUri)
                .retrieve()
                .body(String.class);
    }

    @Override
    public List<MarketCode> getMarketCodes() {
        if (!marketCodes.isEmpty()) {
            return marketCodes;
        }

        String stringMarketCodes = restClient.get()
                .uri(upbitProperties.marketCodeRequestUrl())
                .retrieve()
                .body(String.class);

        log.info("[UpbitApiClientImpl.getMarketCode] marketCodes: {}", stringMarketCodes);

        try {
            marketCodes = objectMapper.readValue(stringMarketCodes, new TypeReference<>() {});
            return marketCodes;
        } catch (Exception e) {
            log.error("[UpbitApiClientImpl.getMarketCode] marketCodes: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
