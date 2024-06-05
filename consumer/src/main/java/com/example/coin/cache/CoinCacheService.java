package com.example.coin.cache;

import com.example.coin.data.message.Candle;
import com.example.coin.data.message.CandleDetail;

import java.util.List;

public interface CoinCacheService {

    /**
     * Performs the role of storing coin information in the cache.
     *
     * @param candles candleDetail List data
     */
    void put(List<Candle> candles);

    /**
     * Retrieves stored coin information from the cache. If it does not exist, returns the candleDetail entered.
     *
     * @param candleDetail  candleDetail data(market, price, time)
     * @return  CandleDetail
     */
    CandleDetail get(CandleDetail candleDetail);
}
