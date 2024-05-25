package com.example.coin.client;

import java.util.List;

public interface CoinApiClient {

    String getCandlesByMinute(String marketCode);

    List<String> getMarketCodes();
}
