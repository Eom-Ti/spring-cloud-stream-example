package com.example.exrate.batch;

import com.example.exrate.client.ExrateApiClient;
import io.micrometer.observation.Observation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class ExrateBatchWorker {
    private static final String MESSAGE_KEY = "exrateData";

    private final ExrateApiClient exrateApiClient;

    @Bean
    public Supplier<Message<String>> createExrateDataSupplier() {
        return () -> {
            String exrateData = this.exrateApiClient.getExrate();
            return MessageBuilder.withPayload(exrateData)
                    .setHeader(KafkaHeaders.KEY, MESSAGE_KEY.getBytes(StandardCharsets.UTF_8))
                    .build();
        };
    }
}
