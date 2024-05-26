package com.example.coin.batch;

import com.example.coin.client.CoinApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class CoinDataSupplier {

    private static final String MESSAGE_KEY = "coinRawData";

    private final CoinApiClient coinApiClient;
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Bean
    public Supplier<Message<String>> createCoinDataSupplier() {
        return () -> {
            List<String> marketCodes = coinApiClient.getMarketCodes();

            List<CompletableFuture<String>> futures = marketCodes.stream()
                    .map(marketCode -> CompletableFuture.supplyAsync(() -> coinApiClient.getCandlesByMinute(marketCode), executorService))
                    .toList();

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

            String combinedResult = allFutures.thenApply(value -> futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.joining()))
                    .join();

            return MessageBuilder.withPayload(combinedResult)
                    .setHeader(KafkaHeaders.KEY, MESSAGE_KEY.getBytes(StandardCharsets.UTF_8))
                    .build();
        };
    }
}
