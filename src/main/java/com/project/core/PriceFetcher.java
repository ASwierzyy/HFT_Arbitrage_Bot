package com.project.core;

public interface PriceFetcher {
    PriceData fetchCurrentPrice(String symbol);
}
