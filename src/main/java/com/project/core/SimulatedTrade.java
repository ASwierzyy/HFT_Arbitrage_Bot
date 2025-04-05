package com.project.core;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name = "trades")
public class SimulatedTrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private BigDecimal entryPrice;
    private BigDecimal exitPrice;
    private String buyExchange;
    private String sellExchange;
    private long timestamp;
    private BigDecimal profit;

    public SimulatedTrade() {}

    public SimulatedTrade(String symbol, BigDecimal entryPrice, BigDecimal exitPrice, String buyExchange, String sellExchange, long timestamp, BigDecimal profit) {
        this.symbol = symbol;
        this.entryPrice = entryPrice;
        this.exitPrice = exitPrice;
        this.buyExchange = buyExchange;
        this.sellExchange = sellExchange;
        this.timestamp = timestamp;
        this.profit = profit;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(BigDecimal entryPrice) {
        this.entryPrice = entryPrice;
    }

    public BigDecimal getExitPrice() {
        return exitPrice;
    }

    public void setExitPrice(BigDecimal exitPrice) {
        this.exitPrice = exitPrice;
    }

    public String getBuyExchange() {
        return buyExchange;
    }

    public void setBuyExchange(String buyExchange) {
        this.buyExchange = buyExchange;
    }

    public String getSellExchange() {
        return sellExchange;
    }

    public void setSellExchange(String sellExchange) {
        this.sellExchange = sellExchange;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "SimulatedTrade: " +
                "symbol: '" + symbol + '\'' +
                ", entryPrice: " + entryPrice +
                ", exitPrice: " + exitPrice +
                ", buyExchange: '" + buyExchange + '\'' +
                ", sellExchange: '" + sellExchange + '\'' +
                ", timestamp: " + timestamp +
                ", profit: " + profit;
    }
}
