package com.example.coin.client;

import com.example.coin.client.data.MarketCode;

import java.util.List;

public interface CoinApiClient {

    String getCandlesByMinute(String marketCode);

    List<MarketCode> getMarketCodes();
}
