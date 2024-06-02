package com.example.coin.log;

import com.example.coin.cache.CoinCacheService;
import com.example.coin.data.Candle;
import io.lettuce.core.api.push.PushListener;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class ExrateLogConsume {

    private final CoinCacheService coinCacheService;

    @Bean
    public Function<List<Candle>, List<Candle>> generateReport() {

        return coinCacheService::put;
    }

    @Bean
    @ConditionalOnProperty(value="spring.cloud.function.definition", havingValue = "generateReport|log")
    public Consumer<String> log() {
        return test -> {
            System.out.println("test2 = " + test);
        };
    }
}
