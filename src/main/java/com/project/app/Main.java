package com.project.app;

import com.project.core.*;
import com.project.util.HibernateTradeLogger;
import com.project.util.TradeLogger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PriceFetcher binance = new BinancePriceFetcher();
        PriceFetcher coinbase = new CoinbasePriceFetcher();
        ArbitrageDetector detector = new SimpleArbitrageDetector();

        TradeLogger logger = new HibernateTradeLogger();

        PriceData priceA = binance.fetchCurrentPrice("BTC/USDT");
        PriceData priceB = coinbase.fetchCurrentPrice("BTC/USD");

        detector.detectArbitrage(priceA, priceB).ifPresent(trade -> {
            logger.logTrade(trade);
            System.out.println("Trade saved to DB:\n" + trade);
        });

        System.out.println("Total profit: " + logger.getTotalProfit());

            List<SimulatedTrade> trades = logger.getAllTrades();
            System.out.println("All Trades:");
            for (SimulatedTrade t : trades) {
                System.out.println(t);
            }
    }
}