package com.example.coin.upbit;

import com.example.coin.data.Candle;
import com.example.coin.functions.ExchangeType;
import com.example.coin.functions.TransformCoinCandleData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class UpbitCoinTransformer {

    private static final String HEADER_KEY = "kafka_messageKey";
    private static final String MESSAGE_KEY = "exrate";

    @Bean
    Function<String, Message<List<Candle>>> transformUpbitCoinData() {
        return rawData -> {
            System.out.println("rawData = " + rawData);
            List<Candle> data = TransformCoinCandleData.toData(rawData, ExchangeType.UP_BIT);
            System.out.println("@@@@@@@@@" + data.toString());
            return MessageBuilder.withPayload(data)
                    .setHeader(HEADER_KEY, MESSAGE_KEY.getBytes(StandardCharsets.UTF_8))
                    .build();
        };
    }
}
