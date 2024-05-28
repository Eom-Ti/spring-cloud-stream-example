package com.example.coin.data;

import java.util.List;

public class Candle {

    private final List<CandleDetail> candleDetails;

    public Candle(List<CandleDetail> candleDetails) {
        this.candleDetails = candleDetails;
    }

    @Override
    public String toString() {
        return "Candle{" +
                "candleDetails=" + candleDetails +
                '}';
    }
}
