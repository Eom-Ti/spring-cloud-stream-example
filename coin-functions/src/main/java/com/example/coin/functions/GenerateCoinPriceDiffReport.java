package com.example.coin.functions;

import com.example.coin.data.log.CoinPriceDiff;

import java.util.List;

public class GenerateCoinPriceDiffReport {

    private GenerateCoinPriceDiffReport() {
    }

    private static final String BLUE_DECREASE_SYMBOL = "\u001B[34m\u25BC\u001B[0m";
    private static final String RED_INCREASE_SYMBOL = "\u001B[31m\u25B2\u001B[0m";
    private static final String MARKET_FIELD = "MARKET : ";
    private static final String DIFF_FIELD = "DIFF : ";
    private static final String TIME_FIELD = "TIME : ";
    private static final String SAME_SYMBOL = "-";
    private static final String SPACE = " ";

    public static String generateCoinPriceDiffReport(List<CoinPriceDiff> coinPriceDiffs) {
        StringBuilder stringBuilder = new StringBuilder();

        for (CoinPriceDiff coinPriceDiff : coinPriceDiffs) {
            stringBuilder.append("\n")
                    .append(getSymbol(coinPriceDiff))
                    .append(SPACE)
                    .append(MARKET_FIELD)
                    .append(coinPriceDiff.market())
                    .append(SPACE)
                    .append(DIFF_FIELD)
                    .append(coinPriceDiff.diff())
                    .append(SPACE)
                    .append(TIME_FIELD)
                    .append(coinPriceDiff.candleTime());
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
