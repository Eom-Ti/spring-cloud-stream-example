package com.example.exrate.client;

import com.example.exrate.client.properties.KrEximProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class KrEximClientImpl implements ExrateApiClient {
    private final RestTemplate restTemplate;
    private final KrEximProperties krEximProperties;
    private final String searchDate = createSearchDate();

    @Override
    public String getExrate() {
        try {
            log.info("[ExrateApiClient] Start getExrate");

            String requestExrateUrl = this.krEximProperties.getRequestExrateUrl();

            URI uri = UriComponentsBuilder.fromUriString(requestExrateUrl)
                    .queryParam(KrEximProperties.AUTH_KEY, this.krEximProperties.authKey())
                    .queryParam(KrEximProperties.SEARCH_DATE, this.searchDate)
                    .queryParam(KrEximProperties.DATA, KrEximProperties.EXRATE_TYPE)
                    .build().toUri();

            String response = this.restTemplate.getForObject(uri, String.class);

            log.info("[ExrateApiClient] End getExrate exrateData : {}", response);
            return response;
        } catch (Exception e) {
            log.error("[ExrateApiClient] has Exception", e);
            throw new RuntimeException("call Api Fail");
        }
    }

    private String createSearchDate() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        return now.format(formatter);
    }
}
