package com.example.coin.upbit.support.kafka;

import com.example.coin.data.message.Candle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.List;

@Slf4j
public class CandleSerializer implements Serializer<List<Candle>> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public byte[] serialize(String topic, List<Candle> candles) {
        try {
            if (candles == null) {
                log.error("candle data must be not null");
                return new byte[0];
            }

            return objectMapper.writeValueAsBytes(candles);
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize candle", e);
        }
    }
}
