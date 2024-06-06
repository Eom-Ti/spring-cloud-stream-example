package com.example.coin.functions.upbit;

import com.example.coin.data.message.Candle;
import com.example.coin.data.message.CandleDetail;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UpbitCandleCustomDeserializer extends JsonDeserializer<List<Candle>> {

    private static final String MARKET_FIELD = "market";
    private static final String PRICE = "opening_price";
    private static final String CANDLE_TIME = "candle_date_time_utc";

    @Override
    public List<Candle> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        List<Candle> candles = new ArrayList<>();

        while (!jsonParser.isClosed()) {
            JsonNode rootNode = jsonParser.readValueAsTree();
            if (rootNode == null) {
                continue;
            }

            Candle candle = StreamSupport.stream(rootNode.spliterator(), false)
                    .map(node -> {
                        String market = node.get(MARKET_FIELD).asText();
                        BigDecimal price = new BigDecimal(node.get(PRICE).asText()).setScale(6, RoundingMode.HALF_UP);
                        ZonedDateTime candleDateTimeUtc = LocalDateTime.parse(node.get(CANDLE_TIME).asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atZone(ZoneId.of("UTC"));

                        return new CandleDetail(market, price, candleDateTimeUtc);
                    }).collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), Candle::new));

            candles.add(candle);
        }
        return candles;
    }
}
