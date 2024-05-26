package com.example.coin.functions.upbit;

import com.example.coin.data.Candle;
import com.example.coin.data.CandleDetail;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

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

        while (jsonParser.nextToken() != null) {
            JsonNode node = jsonParser.readValueAsTree();
            System.out.println("node = " + node.toString());
            // JSON 배열의 각 요소를 Candle 객체로 변환하여 리스트에 추가
                String market = node.get("market").asText();
                ZonedDateTime candleDateTimeUtc = LocalDate.parse(node.get("candle_date_time_utc").asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atStartOfDay(ZoneOffset.UTC);
                BigDecimal price = new BigDecimal(node.get("opening_price").asText()).setScale(6);

                CandleDetail candleDetail = new CandleDetail(market, price, candleDateTimeUtc);

                // Candle 객체 생성하여 CandleDetail 추가
                Candle candle = new Candle();
                candle.addCandleDetail(candleDetail);

                candles.add(candle);
        }
        return candles;
    }
}
