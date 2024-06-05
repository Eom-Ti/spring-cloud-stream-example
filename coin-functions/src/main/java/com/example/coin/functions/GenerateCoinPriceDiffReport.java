package com.example.coin.functions;

import com.example.coin.data.log.CoinPriceDiff;

import java.util.List;

public class GenerateCoinPriceDiffReport {

    private GenerateCoinPriceDiffReport() {
    }

    private static final String BLUE_DECREASE_SYMBOL = "\u001B[34m\u25BC\u001B[0m";
    private static final String RED_INCREASE_SYMBOL = "\u001B[31m\u25B2\u001B[0m";
    private static final String SAME_SYMBOL = "--";

    public static String generateCoinPriceDiffReport(List<CoinPriceDiff> coinPriceDiffs) {
        StringBuilder stringBuilder = new StringBuilder();

        for (CoinPriceDiff coinPriceDiff : coinPriceDiffs) {
            stringBuilder.append(coinPriceDiff.market())
                    .append(getSymbol(coinPriceDiff))
                    .append(coinPriceDiff.diff())
                    .append(coinPriceDiff.candleTime())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private static String getSymbol(CoinPriceDiff coinPriceDiff) {
        if (coinPriceDiff.isSame()) {
            return SAME_SYMBOL;
        } else if (coinPriceDiff.isIncrease()) {
            return RED_INCREASE_SYMBOL;
        } else {
            return BLUE_DECREASE_SYMBOL;
        }
    }
}
