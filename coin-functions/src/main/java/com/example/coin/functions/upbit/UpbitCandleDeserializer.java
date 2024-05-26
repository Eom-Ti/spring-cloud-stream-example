package com.example.coin.functions.upbit;

import com.example.coin.data.CandleDetail;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UpbitCandleDeserializer extends StdDeserializer<CandleDetail> {

    public UpbitCandleDeserializer() {
        super(CandleDetail.class);
    }

    @Override
    public CandleDetail deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        for (JsonNode jsonNode : node) {
            String market = node.get("market").asText();
            ZonedDateTime candleDateTimeUtc = LocalDate.parse(node.get("candle_date_time_utc").asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atStartOfDay(ZoneOffset.UTC);
            BigDecimal price = new BigDecimal(node.get("opening_price").asText()).setScale(6);

            CandleDetail candleDetail = new CandleDetail(market, price, candleDateTimeUtc);

        }




    }
}
