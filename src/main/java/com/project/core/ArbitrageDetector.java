package com.project.core;

import java.util.Optional;

public interface ArbitrageDetector {
    Optional<SimulatedTrade> detectArbitrage(PriceData priceA, PriceData priceB);
}
