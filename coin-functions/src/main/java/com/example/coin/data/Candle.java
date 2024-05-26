package com.example.coin.data;

import java.util.ArrayList;
import java.util.List;

public class Candle {

    private final List<CandleDetail> candleDetails = new ArrayList<>();

    public List<CandleDetail> getCandleDetails() {
        return candleDetails;
    }

    public void addCandleDetail(CandleDetail candleDetail) {
        this.candleDetails.add(candleDetail);
    }

    @Override
    public String toString() {
        return "Candle{" +
                "candleDetails=" + candleDetails +
                '}';
    }
}
