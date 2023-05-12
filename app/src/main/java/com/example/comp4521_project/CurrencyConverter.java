package com.example.comp4521_project;

import java.util.HashMap;

public class CurrencyConverter {
    private static final HashMap<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("CNY", 1 / 0.128);
        exchangeRates.put("JPY", 1 / 0.128 / 100);
        exchangeRates.put("USD", 1 / 7.77);
        exchangeRates.put("HKD", 1.0);
    }

    public static Double toHKD(String currency, Double amount) {
        Double exchangeRate = exchangeRates.getOrDefault(currency, null);

        if (exchangeRate == null || amount == null) {
            return null;
        }

        return amount * exchangeRate;
    }
}
