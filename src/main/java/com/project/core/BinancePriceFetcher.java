package com.project.core;

public class BinancePriceFetcher implements PriceFetcher{
    @Override
    public PriceData fetchCurrentPrice(String symbol) {
        System.out.println("Fetching price from Binance for: " + symbol);
        return null;
    }
}
