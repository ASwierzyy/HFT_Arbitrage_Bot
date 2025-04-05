package com.project.app;

import com.project.core.ArbitrageDetector;
import com.project.core.BinancePriceFetcher;
import com.project.core.CoinbasePriceFetcher;
import com.project.core.SimpleArbitrageDetector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Arbitrage Bot...");

         BinancePriceFetcher binance = new BinancePriceFetcher();
         CoinbasePriceFetcher coinbase = new CoinbasePriceFetcher();
         ArbitrageDetector detector = new SimpleArbitrageDetector();

         System.out.println("Bot initialized.");
    }
}