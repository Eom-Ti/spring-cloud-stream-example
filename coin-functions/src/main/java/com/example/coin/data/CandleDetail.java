package com.example.coin.data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record CandleDetail(
        String market,
        BigDecimal price,
        ZonedDateTime candleTime
) {
}
