package com.example.coin.data.message;

import java.util.List;

public record Candle(
        List<CandleDetail> candleDetails
) {
}
