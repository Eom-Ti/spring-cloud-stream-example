package com.example.coin.data.message;

import java.io.Serializable;
import java.util.List;

public record Candle(
        List<CandleDetail> candleDetails
) implements Serializable {
}
