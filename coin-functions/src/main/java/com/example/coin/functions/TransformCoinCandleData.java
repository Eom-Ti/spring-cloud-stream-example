package com.example.coin.functions;

import com.example.coin.data.Candle;
import com.example.coin.data.CandleDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TransformCoinCandleData {

    private static final Logger log = LoggerFactory.getLogger(TransformCoinCandleData.class);

    private TransformCoinCandleData() {
    }

    public static List<Candle> toData(String rawData, ExchangeType exchangeType) {
        log.info("[TransformCoinData.toData] exchangeType : {}, rawData: {}", exchangeType, rawData);

        ObjectMapper objectMapper = exchangeType.getObjectMapper();
        try {
            return objectMapper.readValue(rawData, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error("[exrate-functions][TransformExrateData] faile readValue", e);
            throw new RuntimeException(e);
        }
    }
}
