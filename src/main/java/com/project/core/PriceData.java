package com.project.core;

import java.math.BigDecimal;

public class PriceData {
    private String symbol;
    private BigDecimal price;
    private long timestamp;
    private String exchange;


    public PriceData(String symbol, BigDecimal price, long timestamp, String exchange) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "PriceData{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                ", exchange='" + exchange + '\'' +
                '}';
    }
}
