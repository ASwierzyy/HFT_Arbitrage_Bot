package com.project.core;

public class CoinbasePriceFetcher implements PriceFetcher{
    @Override
    public PriceData fetchCurrentPrice(String symbol) {
        System.out.println("Fetching price from Coinbase for: " + symbol);
        return null;
    }
}
