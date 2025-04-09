package com.project.app;

import com.project.core.*;
import com.project.util.HibernateTradeLogger;
import com.project.util.TradeLogger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PriceFetcher binance = new BinancePriceFetcher();
        PriceFetcher coinbase = new CoinbasePriceFetcher();
        ArbitrageDetector detector = new SimpleArbitrageDetector();
        TradeLogger logger = new HibernateTradeLogger();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable botTask = () -> {
            try {
                PriceData priceA = binance.fetchCurrentPrice("BTC/USDT");
                PriceData priceB = coinbase.fetchCurrentPrice("BTC/USD");

                System.out.println("Binance:  " + priceA);
                System.out.println("Coinbase: " + priceB);

                detector.detectArbitrage(priceA, priceB).ifPresent(t -> {
                    logger.logTrade(t);
                    System.out.println("Arbitrage Executed: " + t);
                });

                System.out.println("Total profit: " + logger.getTotalProfit());
    //            List<SimulatedTrade> trades = logger.getAllTrades();
    //            System.out.println("All Trades:");
    //            for (SimulatedTrade t : trades) {
    //                System.out.println(t);
    //            }
                System.out.println("—".repeat(60));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        scheduler.scheduleAtFixedRate(botTask, 0, 5, TimeUnit.SECONDS);
        Thread.currentThread().join();
    }
}