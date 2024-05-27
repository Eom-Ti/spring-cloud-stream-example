package com.example.coin.functions.upbit;

import com.example.coin.data.Candle;
import com.example.coin.data.CandleDetail;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UpbitCandleDeserializer extends JsonDeserializer<List<Candle>> {

    @Override
    public List<Candle> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        List<Candle> candles = new ArrayList<>();

        while (!jsonParser.isClosed()) {
            JsonNode rooNode = jsonParser.readValueAsTree();

            if (rooNode == null) {
                continue;
            }

            for (JsonNode node : rooNode) {
                String market = node.get("market").asText();
                ZonedDateTime candleDateTimeUtc = LocalDate.parse(node.get("candle_date_time_utc").asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atStartOfDay(ZoneOffset.UTC);
                BigDecimal price = new BigDecimal(node.get("opening_price").asText()).setScale(6);

                CandleDetail candleDetail = new CandleDetail(market, price, candleDateTimeUtc);
                Candle candle = new Candle();
                candle.addCandleDetail(candleDetail);

                candles.add(candle);
            }
        }
        return candles;
    }
}
