package com.project.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class SimpleArbitrageDetector implements ArbitrageDetector{

    private static final BigDecimal  TRADING_FEE = new BigDecimal("0.001");
    private static final BigDecimal MIN_PROFIT = new BigDecimal("1.00");

    @Override
    public Optional<SimulatedTrade> detectArbitrage(PriceData priceA, PriceData priceB) {
        Optional<SimulatedTrade> trade1 = tryArbitrage(priceA,priceB);
        if(trade1.isPresent()) return trade1;

        Optional<SimulatedTrade> trade2 = tryArbitrage(priceB,priceA);
        return trade2;
    }

    private Optional<SimulatedTrade> tryArbitrage(PriceData buyFrom, PriceData sellTo){
        BigDecimal buyPrice = buyFrom.getPrice().multiply(BigDecimal.ONE.add(TRADING_FEE));
        BigDecimal sellPrice = sellTo.getPrice().multiply(BigDecimal.ONE.subtract(TRADING_FEE));

        BigDecimal profit = sellPrice.subtract(buyPrice);

        if (profit.compareTo(MIN_PROFIT) > 0){
            return Optional.of(new SimulatedTrade(
                    buyFrom.getSymbol(),
                    buyFrom.getPrice(),
                    sellTo.getPrice(),
                    buyFrom.getExchange(),
                    sellTo.getExchange(),
                    System.currentTimeMillis(),
                    profit.setScale(2, RoundingMode.HALF_UP)
            ));
        }
        return Optional.empty();
    }
}
