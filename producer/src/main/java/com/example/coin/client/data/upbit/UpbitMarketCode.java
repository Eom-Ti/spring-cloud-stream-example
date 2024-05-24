package com.example.coin.client.data.upbit;

import com.example.coin.client.data.MarketCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpbitMarketCode implements MarketCode {

    @JsonProperty("market")
    private String code;
}
