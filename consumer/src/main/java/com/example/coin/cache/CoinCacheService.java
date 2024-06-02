package com.example.coin.cache;

import com.example.coin.data.Candle;

import java.util.List;

public interface CoinCacheService {

    List<Candle> put(List<Candle> candles);
}
