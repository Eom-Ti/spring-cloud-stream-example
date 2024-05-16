package com.example.functions;

import com.example.data.ExRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TransformExrateData {
    private TransformExrateData() {
    }

    private static final Logger log = LoggerFactory.getLogger(TransformExrateData.class);

    public static List<ExRate> toData(String rawData) {
        log.info("[exrate-functions][TransformExrateData] rawData : {}", rawData);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(rawData, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error("[exrate-functions][TransformExrateData] faile readValue", e);
            throw new RuntimeException(e);
        }
    }
}
