package com.example.coin.log;

import com.example.coin.cache.CoinCacheService;
import com.example.coin.data.message.Candle;
import com.example.coin.data.message.CandleDetail;
import com.example.coin.functions.GenerateCoinPriceDiffReport;
import com.example.coin.data.log.CoinPriceDiff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExrateLogConsume {

    private final CoinCacheService coinCacheService;

    @Bean
    public Function<List<Candle>, String> generateReport() {
        List<CoinPriceDiff> coinPriceDiffs = new ArrayList<>();

        return candles -> {
            candles.forEach(candle -> candle.candleDetails().forEach(candleDetail -> {
                CandleDetail cacheCandleDetail = coinCacheService.get(candleDetail);
                BigDecimal coinDiff = cacheCandleDetail.calcPriceDiff(candleDetail);

                CoinPriceDiff coinPriceDiff = new CoinPriceDiff(candleDetail.market(), coinDiff, candleDetail.candleTime());
                coinPriceDiffs.add(coinPriceDiff);
            }));
            coinCacheService.put(candles);
            return GenerateCoinPriceDiffReport.generateCoinPriceDiffReport(coinPriceDiffs);
        };
    }

    @Bean
    @ConditionalOnProperty(value="spring.cloud.function.definition", havingValue = "generateReport|log")
    public Consumer<String> log() {
        return log::info;
    }
}
