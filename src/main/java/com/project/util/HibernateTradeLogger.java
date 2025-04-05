package com.project.util;

import com.project.core.SimulatedTrade;
import com.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class HibernateTradeLogger implements TradeLogger {

    @Override
    public void logTrade(SimulatedTrade trade) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(trade);
            tx.commit();
        }
    }

    @Override
    public List<SimulatedTrade> getAllTrades() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM SimulatedTrade", SimulatedTrade.class).list();
        }
    }

    @Override
    public BigDecimal getTotalProfit() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            BigDecimal total = session.createQuery(
                            "SELECT COALESCE(SUM(t.profit), 0) FROM SimulatedTrade t", BigDecimal.class)
                    .getSingleResult();
            return total;
        }
    }
}