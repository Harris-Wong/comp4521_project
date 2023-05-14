package com.example.comp4521_project;

import java.util.HashMap;

public class CurrencyConverter {
    private static final HashMap<Currency, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put(Currency.CNY, 1 / 1.13);
        exchangeRates.put(Currency.JPY, 1 / 0.058);
        exchangeRates.put(Currency.USD, 1 / 7.77);
        exchangeRates.put(Currency.HKD, 1.0);
    }

    public static Double toHKD(Currency currency, Double amountInCurrency) {
        Double exchangeRate = exchangeRates.getOrDefault(currency, null);

        if (exchangeRate == null || amountInCurrency == null) {
            return null;
        }

        return amountInCurrency / exchangeRate;
    }

    public static Double hkdTo(Currency currency, Double amountInHKD) {
        Double exchangeRate = exchangeRates.getOrDefault(currency, null);
        if (exchangeRate == null || amountInHKD == null) {
            return null;
        }
        return amountInHKD * exchangeRate;
    }
}
