package com.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExRate {

    @JsonProperty("result")
    private int result;

    @JsonProperty("cur_unit")
    private String curUnit;

    @JsonProperty("ttb")
    private String ttb;

    @JsonProperty("tts")
    private String tts;

    @JsonProperty("deal_bas_r")
    private String dealBasr;

    @JsonProperty("bkpr")
    private String bkpr;

    @JsonProperty("yy_efee_r")
    private String yyEfeer;

    @JsonProperty("ten_dd_efee_r")
    private String tenDdEfeer;

    @JsonProperty("kftc_bkpr")
    private String kftcBkpr;

    @JsonProperty("kftc_deal_bas_r")
    private String kftcDealBasr;

    @JsonProperty("cur_nm")
    private String curNm;

    public int getResult() {
        return result;
    }

    public String getCurUnit() {
        return curUnit;
    }

    public String getTtb() {
        return ttb;
    }

    public String getTts() {
        return tts;
    }

    public String getDealBasr() {
        return dealBasr;
    }

    public String getBkpr() {
        return bkpr;
    }

    public String getYyEfeer() {
        return yyEfeer;
    }

    public String getTenDdEfeer() {
        return tenDdEfeer;
    }

    public String getKftcBkpr() {
        return kftcBkpr;
    }

    public String getKftcDealBasr() {
        return kftcDealBasr;
    }

    public String getCurNm() {
        return curNm;
    }
}
