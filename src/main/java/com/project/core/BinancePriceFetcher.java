package com.project.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BinancePriceFetcher implements PriceFetcher{

    private static final String BASE_URL = "https://api.binance.com/api/v3/ticker/price";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public PriceData fetchCurrentPrice(String symbol) throws IOException, InterruptedException {
        String querySymbol = symbol.replace("/","");
        URI uri = URI.create(BASE_URL+"?symbol="+querySymbol);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        BinanceResponse binanceResponse = objectMapper.readValue(response.body(),BinanceResponse.class);
        return new PriceData(binanceResponse.symbol,
                new BigDecimal(binanceResponse.price),
                System.currentTimeMillis(),
                "Binance");
    }

    private static class BinanceResponse {
        public String symbol;
        public String price;
    }
}
