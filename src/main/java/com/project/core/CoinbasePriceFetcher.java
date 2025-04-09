package com.project.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoinbasePriceFetcher implements PriceFetcher{

    private static final String BASE_URL = "https://api.coinbase.com/v2/prices";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PriceData fetchCurrentPrice(String symbol) throws IOException, InterruptedException {
        String querySymbol = symbol.replace("/","-");
        URI uri = URI.create(BASE_URL+"/"+querySymbol+"/spot");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200){
            CoinbaseWrapperResponse wrapper = objectMapper.readValue(response.body(), CoinbaseWrapperResponse.class);
            CoinbaseResponse coinbaseData = wrapper.data;

            return new PriceData(coinbaseData.base + "/" + coinbaseData.currency,
                    new BigDecimal(coinbaseData.amount),
                    System.currentTimeMillis(),
                    "Coinbase");
        }else throw new IOException("Coinbase API returned error: " + response.statusCode());


    }

    private static class CoinbaseWrapperResponse {
        public CoinbaseResponse data;
    }

    private static class CoinbaseResponse {
        public String base;
        public String currency;
        public String amount;
    }
}
