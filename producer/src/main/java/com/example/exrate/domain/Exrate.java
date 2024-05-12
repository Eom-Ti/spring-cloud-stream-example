package com.example.exrate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Exrate {
    private Integer result;
    private String curUnit;
    private String curNm;
    private String ttb;
    private String tts;
}
