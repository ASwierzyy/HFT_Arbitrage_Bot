package com.project.core;

import java.io.IOException;

public interface PriceFetcher {
    PriceData fetchCurrentPrice(String symbol) throws IOException, InterruptedException;
}
