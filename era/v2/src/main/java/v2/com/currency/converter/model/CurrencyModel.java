package v2.com.currency.converter.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyModel {
    private static final String API_KEY = "fca_live_1uaFq4pS64lCGszOYA49JdzEDcf02woyQZOI7rT9";
    private static final String BASE_URL = "https://api.freecurrencyapi.com/v1/latest";
    private static final String BASE_CURRENCY = "USD";
    private static final String TARGET_CURRENCY = "INR";
    
    private double exchangeRate;
    
    public CurrencyModel() {
        fetchExchangeRate();
    }
    
    private void fetchExchangeRate() {
        try {
            String urlString = String.format("%s?apikey=%s&base_currency=%s&currencies=%s", 
                BASE_URL, API_KEY, BASE_CURRENCY, TARGET_CURRENCY);
            
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
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
            exchangeRate = data.getDouble(TARGET_CURRENCY);
            
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to a default rate if API fails
            exchangeRate = 83.0;
        }
    }
    
    public double convertINRToDollar(double inr) {
        return Math.floor(inr / exchangeRate);
    }
    
    public double convertDollarToINR(double dollar) {
        return Math.floor(dollar * exchangeRate);
    }
    
    public void refreshExchangeRate() {
        fetchExchangeRate();
    }
} 