package com.example.coin.functions;

import com.example.coin.data.CandleDetail;
import com.example.coin.functions.upbit.UpbitCandleDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public enum ExchangeType {

    UPBIT() {
        @Override
        public ObjectMapper getObjectMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule customModule = new SimpleModule();
            customModule.addDeserializer(CandleDetail.class, new UpbitCandleDeserializer());

            objectMapper.registerModule(customModule);
            return objectMapper;
        }
    },
    BITHUMB() {
        @Override
        public ObjectMapper getObjectMapper() {
            return null;
        }
    };

    public abstract ObjectMapper getObjectMapper();
}
