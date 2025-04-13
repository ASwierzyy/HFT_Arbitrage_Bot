# 💸 Crypto Arbitrage Bot

**Crypto Arbitrage Bot** is a real-time Java application that simulates profitable arbitrage opportunities between Binance and Coinbase. It fetches BTC/USDT prices via REST APIs, detects buy/sell gaps, and logs simulated trades with calculated profit.

---

## 🚀 Features

- 🔄 Live BTC/USDT price fetching from Binance & Coinbase (REST)
- 🧠 Arbitrage detection with fee threshold and two-way checks
- 💾 Trade simulation with profit calculation
- 🗃️ Persistent logging to H2 database (via Hibernate)
- 💰 Real-time tracking of total accumulated profit
- 🧪 Unit-tested core logic (arbitrage + trade logging)

---

## 🧱 Tech Stack

### 🔧 Backend
- Java 23
- REST API (Java `HttpClient`)
- Jackson (JSON parsing)
- Hibernate ORM + H2 (file-based SQL)
- ScheduledExecutorService
- BigDecimal (for financial precision)
- JUnit 5

---

## 📈 Example Use Case

- Binance: $83,000  
- Coinbase: $82,700  
✅ Buy on Coinbase → Sell on Binance → Profit logged & tracked

---
