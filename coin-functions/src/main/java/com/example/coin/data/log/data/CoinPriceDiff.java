package com.example.coin.data.log.data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record CoinPriceDiff(
        String market,
        BigDecimal diff,
        ZonedDateTime candleTime
) {
    public boolean isIncrease() {
        return diff.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isSame() {
        return diff.equals(BigDecimal.ZERO);
    }
}
