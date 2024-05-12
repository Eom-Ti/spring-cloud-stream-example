package com.example.exrate.client;

import com.example.exrate.client.data.ExrateDataResult;
import com.example.exrate.client.properties.KrEximProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class KrEximClientImpl implements ExrateApiClient {

    private static final String EXRATE_TYPE = "AP01";

    private final RestTemplate restTemplate;
    private final KrEximProperties krEximProperties;

    @Override
    public ExrateDataResult getExrate() {
        try {
            log.info("[ExrateApiClient] Start getExrate");

            String requestExrateUrl = krEximProperties.getRequestExrateUrl();

            URI uri = UriComponentsBuilder.fromUriString(requestExrateUrl)
                    .queryParam("authkey", krEximProperties.authKey())
                    .queryParam("searchdate", EXRATE_TYPE)
                    .build().toUri();

            ExrateDataResult exrateDataResult = restTemplate.getForObject(uri, ExrateDataResult.class);

            log.info("[ExrateApiClient] End getExrate exrateData : {}", exrateDataResult);
            return exrateDataResult;
        } catch (Exception e) {
            log.error("[ExrateApiClient] has Exception", e);
            throw new RuntimeException("call Api Fail");
        }
    }
}
