package com.example.exrate.batch;

import com.example.exrate.client.ExrateApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class ExrateBatchWorker {

    private final ExrateApiClient exrateApiClient;

    @Bean
    public Supplier<String> createExrateDataSupplier() {
        return exrateApiClient::getExrate;
    }
}
