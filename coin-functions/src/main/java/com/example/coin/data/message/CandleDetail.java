package com.example.coin.data.message;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record CandleDetail(
        String market,
        BigDecimal price,
        ZonedDateTime candleTime
) implements Serializable  {
    public BigDecimal calcPriceDiff(CandleDetail candleDetail) {
        if (!market.equals(candleDetail.market)) {
            throw new IllegalArgumentException("market is not equal market");
        }

        return price.subtract(candleDetail.price);
    }
}
