package com.project.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class SimpleArbitrageDetectorTest {
    private ArbitrageDetector detector;

    private long now() {
        return System.currentTimeMillis();
    }

    @BeforeEach
    void setUp() {
        detector = new SimpleArbitrageDetector();
    }

    @Test
    void shouldDetectValidArbitrageOpportunity() {
        PriceData buy = new PriceData("BTC/USDT", new BigDecimal("82000"), now(), "Coinbase");
        PriceData sell = new PriceData("BTC/USDT", new BigDecimal("83500"), now(), "Binance");

        Optional<SimulatedTrade> trade = detector.detectArbitrage(buy, sell);

        assertTrue(trade.isPresent(), "Expected arbitrage opportunity to be detected");
        assertEquals("Coinbase", trade.get().getBuyExchange());
        assertEquals("Binance", trade.get().getSellExchange());
    }

    @Test
    void shouldNotDetectArbitrageIfProfitIsBelowThreshold() {
        PriceData buy = new PriceData("BTC/USDT", new BigDecimal("82000"), now(), "Coinbase");
        PriceData sell = new PriceData("BTC/USDT", new BigDecimal("82100"), now(), "Binance");

        Optional<SimulatedTrade> trade = detector.detectArbitrage(buy, sell);

        assertFalse(trade.isPresent(), "Expected no arbitrage (profit below threshold)");
    }

    @Test
    void shouldNotDetectArbitrageWhenFeesCancelProfit() {
        PriceData buy = new PriceData("BTC/USDT", new BigDecimal("82000"), now(), "ExchangeA");
        PriceData sell = new PriceData("BTC/USDT", new BigDecimal("82100"), now(), "ExchangeB");

        Optional<SimulatedTrade> trade = detector.detectArbitrage(buy, sell);

        assertFalse(trade.isPresent(), "Expected no arbitrage (fees negate profit)");
    }

    @Test
    void shouldDetectReverseArbitrageDirection() {
        PriceData buy = new PriceData("BTC/USDT", new BigDecimal("82000"), now(), "Binance");
        PriceData sell = new PriceData("BTC/USDT", new BigDecimal("84000"), now(), "Coinbase");

        Optional<SimulatedTrade> trade = detector.detectArbitrage(sell, buy);
        assertTrue(trade.isPresent(), "Expected arbitrage in reverse direction");
        assertEquals("Binance", trade.get().getBuyExchange());
        assertEquals("Coinbase", trade.get().getSellExchange());
    }

    @Test
    void shouldNotDetectArbitrageWhenPricesAreEqual() {
        BigDecimal price = new BigDecimal("83000");
        PriceData buy = new PriceData("BTC/USDT", price, now(), "ExchangeA");
        PriceData sell = new PriceData("BTC/USDT", price, now(), "ExchangeB");

        Optional<SimulatedTrade> trade = detector.detectArbitrage(buy, sell);

        assertFalse(trade.isPresent(), "Expected no arbitrage (prices are equal)");
    }
}