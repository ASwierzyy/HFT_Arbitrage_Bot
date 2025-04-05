package com.project.app;

import com.project.core.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Starting Arbitrage Bot...");

        PriceFetcher binanceFetcher = new BinancePriceFetcher();
        PriceData binancePrice = binanceFetcher.fetchCurrentPrice("BTC/USDT");
        System.out.println(binancePrice);

        PriceFetcher coinbaseFetcher = new CoinbasePriceFetcher();
        PriceData price = coinbaseFetcher.fetchCurrentPrice("BTC/USD");
        System.out.println(price);


    }
}