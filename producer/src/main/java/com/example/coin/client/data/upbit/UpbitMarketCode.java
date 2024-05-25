package com.example.coin.client.data.upbit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpbitMarketCode {

    @JsonProperty("market")
    private String code;
}
