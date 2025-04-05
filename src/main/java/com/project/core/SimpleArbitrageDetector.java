package com.project.core;

import java.util.Optional;

public class SimpleArbitrageDetector implements ArbitrageDetector{
    @Override
    public Optional<SimulatedTrade> detectArbitrage(PriceData priceA, PriceData priceB) {
        return Optional.empty();
    }
}
