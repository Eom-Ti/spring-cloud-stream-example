package com.example.coin.data;

import java.util.List;

public record Candle(
        List<CandleDetail> candleDetails
) {
}
