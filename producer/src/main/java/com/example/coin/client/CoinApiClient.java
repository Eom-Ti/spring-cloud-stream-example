package com.example.coin.client;

import java.util.List;

public interface CoinApiClient {

    /**
     * Gets coin price information in fractions.
     *
     * @param marketCode marketCode ex)KRW-ETC
     * @return  String Type CandleData
     */
    String getCandlesByMinute(String marketCode);

    /**
     * Gets the market code provided by the current exchange.
     *
     * @return List<String> marketCodes
     */
    List<String> getMarketCodes();
}
