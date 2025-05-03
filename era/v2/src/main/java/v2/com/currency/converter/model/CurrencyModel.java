package v2.com.currency.converter.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class CurrencyModel {
    private static final String API_KEY = "fca_live_1uaFq4pS64lCGszOYA49JdzEDcf02woyQZOI7rT9"; // Replace with your key if needed
    private static final String BASE_URL = "https://api.freecurrencyapi.com/v1/latest";
    private static final String BASE_CURRENCY = "USD";
    // Define the currencies we want to support
    private static final List<String> SUPPORTED_CURRENCIES = Arrays.asList(
        "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "INR" // Added INR explicitly
    );

    // Store exchange rates relative to the BASE_CURRENCY (USD)
    private Map<String, Double> exchangeRates = new HashMap<>();

    public CurrencyModel() {
        fetchExchangeRate();
    }

    private void fetchExchangeRate() {
        // Filter out the base currency from the list for the API call if needed by API
        String targetCurrenciesString = SUPPORTED_CURRENCIES.stream()
            .filter(c -> !c.equals(BASE_CURRENCY))
            .collect(Collectors.joining(","));

        if (targetCurrenciesString.isEmpty()) {
            System.err.println("No target currencies specified excluding the base currency.");
             // Initialize with base currency rate only if no other targets
            exchangeRates.put(BASE_CURRENCY, 1.0);
            // Consider adding default fallback rates here
            addDefaultFallbackRates();
            return;
        }


        try {
            String urlString = String.format("%s?apikey=%s&base_currency=%s&currencies=%s",
                BASE_URL, API_KEY, BASE_CURRENCY, targetCurrenciesString);

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds connection timeout
            connection.setReadTimeout(5000);    // 5 seconds read timeout

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject data = jsonResponse.getJSONObject("data");

            // Clear previous rates and add the base currency rate
            exchangeRates.clear();
            exchangeRates.put(BASE_CURRENCY, 1.0); // Rate of base currency to itself is 1

            // Add fetched rates for other currencies
            for (String currency : data.keySet()) {
                 if (SUPPORTED_CURRENCIES.contains(currency)) { // Ensure we only add supported ones
                     exchangeRates.put(currency, data.getDouble(currency));
                 }
            }

             // Ensure all supported currencies have an entry, even if API didn't return them (use default/fallback)
            for (String currency : SUPPORTED_CURRENCIES) {
                exchangeRates.putIfAbsent(currency, getDefaultRate(currency));
            }


        } catch (Exception e) {
            System.err.println("API Error fetching exchange rates: " + e.getMessage());
            e.printStackTrace();
            // Fallback to default rates if API fails
            addDefaultFallbackRates();
        }
    }

     // Helper method to provide default rates in case of API failure
    private void addDefaultFallbackRates() {
        System.out.println("API fetch failed or returned incomplete data. Using default fallback rates.");
        exchangeRates.clear(); // Clear any partial data
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("INR", 83.50);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("JPY", 157.0);
        exchangeRates.put("GBP", 0.79);
        exchangeRates.put("AUD", 1.50);
        exchangeRates.put("CAD", 1.37);
        exchangeRates.put("CHF", 0.89);
        exchangeRates.put("CNY", 7.25);
        exchangeRates.put("SEK", 10.50);
        exchangeRates.put("NZD", 1.62);
        // Add more defaults if needed
    }

     // Helper to get a single default rate
    private double getDefaultRate(String currency) {
        // Return a reasonable default or NaN/exception if critical
        switch (currency) {
            case "USD": return 1.0;
            case "INR": return 83.50;
            case "EUR": return 0.92;
            case "JPY": return 157.0;
            case "GBP": return 0.79;
            case "AUD": return 1.50;
            case "CAD": return 1.37;
            case "CHF": return 0.89;
            case "CNY": return 7.25;
            case "SEK": return 10.50;
            case "NZD": return 1.62;
            default:
                System.err.println("Warning: No default rate found for " + currency + ". Returning 0.0.");
                return 0.0; // Or throw an exception
        }
    }


    /**
     * Converts an amount from one currency to another.
     *
     * @param amount       The amount to convert.
     * @param fromCurrency The currency code to convert from (e.g., "USD").
     * @param toCurrency   The currency code to convert to (e.g., "INR").
     * @return The converted amount, or Double.NaN if conversion is not possible.
     */
    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            System.err.println("Error: Conversion between " + fromCurrency + " and " + toCurrency + " is not supported or rates are missing.");
            return Double.NaN; // Indicate error or unsupported conversion
        }

        double rateFrom = exchangeRates.get(fromCurrency);
        double rateTo = exchangeRates.get(toCurrency);

        if (rateFrom == 0) {
             System.err.println("Error: Exchange rate for " + fromCurrency + " is zero, cannot perform conversion.");
             return Double.NaN;
        }


        // Convert amount to base currency (USD) first
        double amountInBase = amount / rateFrom;

        // Convert from base currency to target currency
        double convertedAmount = amountInBase * rateTo;

        // Optional: Round to a reasonable number of decimal places
        // return Math.round(convertedAmount * 100.0) / 100.0;
         return convertedAmount; // Return full precision for now

    }

    /**
     * Refreshes the exchange rates from the API.
     */
    public void refreshExchangeRate() {
        System.out.println("Refreshing exchange rates...");
        fetchExchangeRate();
         System.out.println("Exchange rates refreshed. Current USD to INR: " + exchangeRates.getOrDefault("INR", 0.0));
    }

    /**
     * Returns the set of supported currency codes.
     * @return A Set of supported currency codes (e.g., "USD", "EUR", "INR").
     */
     public Set<String> getSupportedCurrencies() {
        // Return the keys from the map, which represent the successfully loaded/defaulted currencies
        return exchangeRates.keySet();
     }

     /**
     * Gets the current exchange rate between two specified currencies.
     * Useful for display purposes.
     * @param fromCurrency The base currency.
     * @param toCurrency The target currency.
     * @return The exchange rate (how many units of toCurrency per one unit of fromCurrency), or Double.NaN if not available.
     */
     public double getExchangeRate(String fromCurrency, String toCurrency) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            return Double.NaN;
        }
        double rateFrom = exchangeRates.get(fromCurrency); // Rate relative to USD
        double rateTo = exchangeRates.get(toCurrency);     // Rate relative to USD

         if (rateFrom == 0) {
            return Double.NaN; // Avoid division by zero
         }

        // Calculate direct rate: (rateTo / rateFrom) gives units of 'toCurrency' per 'fromCurrency'
        return rateTo / rateFrom;
     }
} 