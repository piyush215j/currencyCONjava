package com.currency.converter.model;

public class CurrencyModel {
    private static final double EXCHANGE_RATE = 65.25;

    public static double convertINRToDollar(double inr) {
        return Math.floor(inr / EXCHANGE_RATE);
    }

    public static double convertDollarToINR(double dollar) {
        return Math.floor(dollar * EXCHANGE_RATE);
    }
} 