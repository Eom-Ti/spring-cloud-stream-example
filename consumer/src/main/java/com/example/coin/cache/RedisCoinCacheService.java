package com.example.coin.cache;

import com.example.coin.data.Candle;
import com.example.coin.data.CandleDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RedisCoinCacheService implements CoinCacheService {

    private final RedisTemplate<String, CandleDetail> redisTemplate;

    @Override
    public List<Candle> put(List<Candle> candles) {
        candles.forEach(candle -> candle.candleDetails().forEach(candleDetail -> {
            String market = candleDetail.market();
            redisTemplate.opsForValue().set(market, candleDetail);
        }));

        return candles;
    }
}
