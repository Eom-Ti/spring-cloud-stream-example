package com.example.coin.batch;

import com.example.coin.client.CoinApiClient;
import com.example.coin.client.data.MarketCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class CoinDataSupplier {

    private final CoinApiClient coinApiClient;

    @Bean
    public Supplier<Message<String>> createCoinDataSupplier() {
        return () -> {
            List<MarketCode> marketCodes = coinApiClient.getMarketCodes();

            marketCodes.stream()
                    .map(marketCode -> )
        }
    }
}
