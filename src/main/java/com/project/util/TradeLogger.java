package com.project.util;

import com.project.core.SimulatedTrade;

import java.math.BigDecimal;
import java.util.List;

public interface TradeLogger {
    void logTrade(SimulatedTrade trade);
    List<SimulatedTrade> getAllTrades();
    BigDecimal getTotalProfit();
}
