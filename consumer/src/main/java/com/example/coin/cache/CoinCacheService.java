package com.example.coin.cache;

import com.example.coin.data.Candle;
import com.example.coin.data.CandleDetail;

import java.util.List;

public interface CoinCacheService {

    void put(List<Candle> candles);

    CandleDetail get(CandleDetail candleDetail);
}
