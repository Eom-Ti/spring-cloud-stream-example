package com.example.exrate.transformer;

import com.example.data.ExRate;
import com.example.functions.TransformExrateData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

@Configuration
public class ExrateTransformer {

    private static final String HEADER_KEY = "kafka_messageKey";
    private static final String MESSAGE_KEY = "exrate";

    @Bean
    Function<String, Message<List<ExRate>>> transformExrateData() {
        return rawData -> {
            List<ExRate> exRates = TransformExrateData.toData(rawData);
            return MessageBuilder.withPayload(exRates)
                    .setHeader(HEADER_KEY, MESSAGE_KEY.getBytes(StandardCharsets.UTF_8))
                    .build();
        };
    }
}
