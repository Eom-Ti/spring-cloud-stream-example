package com.example.coin.functions.enums;

import com.example.coin.functions.upbit.UpbitCandleCustomDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.List;

public enum ExchangeType {

    UP_BIT {
        protected ObjectMapper createObjectMapper() {
            SimpleModule customModule = new SimpleModule();
            customModule.addDeserializer(List.class, new UpbitCandleCustomDeserializer());

            return new ObjectMapper().registerModule(customModule);
        }
    },
    BIT_THUMB {
        protected ObjectMapper createObjectMapper() {
            return null;
        }
    };

    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = createObjectMapper();
        }
        return objectMapper;
    }

    protected abstract ObjectMapper createObjectMapper();
}
