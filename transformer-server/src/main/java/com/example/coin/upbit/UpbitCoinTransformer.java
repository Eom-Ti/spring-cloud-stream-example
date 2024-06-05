package com.example.coin.upbit;

import com.example.coin.data.message.Candle;
import com.example.coin.functions.ExchangeType;
import com.example.coin.functions.TransformCoinCandleData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class UpbitCoinTransformer {

    private static final String HEADER_KEY = "kafka_messageKey";
    private static final String MESSAGE_KEY = "exrate";

    @Bean
    Function<String, Message<List<Candle>>> transformUpbitCoinData() {
        return rawData -> {
            log.info("Transforming upbit coin data");
            List<Candle> candles = TransformCoinCandleData.toData(rawData, ExchangeType.UP_BIT);
            log.info("Transforming upbit coin data completed : {}", candles);

            return MessageBuilder.withPayload(candles)
                    .setHeader(HEADER_KEY, MESSAGE_KEY.getBytes(StandardCharsets.UTF_8))
                    .build();
        };
    }
}
