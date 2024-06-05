package com.example.coin.cache;

import com.example.coin.data.message.Candle;
import com.example.coin.data.message.CandleDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisCoinCacheService implements CoinCacheService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void put(List<Candle> candles) {
        candles.forEach(candle -> candle.candleDetails().forEach(candleDetail -> {
            String market = candleDetail.market();
            redisTemplate.opsForValue().set(market, candleDetail, 20000, TimeUnit.MILLISECONDS);
        }));
    }

    @Override
    public CandleDetail get(CandleDetail candleDetail) {
        Object cacheResult = redisTemplate.opsForValue().get(candleDetail.market());

        if(cacheResult == null) {
            return candleDetail;
        }

        return objectMapper.convertValue(cacheResult, CandleDetail.class);
    }
}
