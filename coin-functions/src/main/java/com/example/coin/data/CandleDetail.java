package com.example.coin.data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CandleDetail {

    private String market;

    private BigDecimal price;

    private ZonedDateTime candleTime;

    public CandleDetail(String market, BigDecimal price, ZonedDateTime candleTime) {
        this.market = market;
        this.price = price;
        this.candleTime = candleTime;
    }

    @Override
    public String toString() {
        return "CandleDetail{" +
                "market='" + market + '\'' +
                ", price=" + price +
                ", candleTime=" + candleTime +
                '}';
    }
}
