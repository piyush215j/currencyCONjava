package v3.src.main.java.v3.com.crypto.converter.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class CryptoModel {
    private static final String API_KEY = "a63e49f18bed1b2dc6826d20eed74481"; // Replace with your actual API key
    private static final String BASE_URL = "http://api.coinlayer.com/live";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00000000");

    public String convertCrypto(String fromCrypto, String toCrypto, double amount) {
        try {
            String urlString = BASE_URL + "?access_key=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response manually since we're getting JSON parsing errors
                String jsonStr = response.toString();
                
                // Check if the response contains an error message
                if (jsonStr.contains("\"success\":false")) {
                    if (jsonStr.contains("\"error\":")) {
                        int startIndex = jsonStr.indexOf("\"error\":") + 9;
                        int endIndex = jsonStr.indexOf("\"", startIndex);
                        String error = jsonStr.substring(startIndex, endIndex);
                        return "Error: " + error;
                    }
                    return "Error: API request failed";
                }

                // Extract rates manually
                double btcRate = 0.0;
                double ethRate = 0.0;

                if (jsonStr.contains("\"BTC\":")) {
                    int startIndex = jsonStr.indexOf("\"BTC\":") + 6;
                    int endIndex = jsonStr.indexOf(",", startIndex);
                    if (endIndex == -1) endIndex = jsonStr.indexOf("}", startIndex);
                    btcRate = Double.parseDouble(jsonStr.substring(startIndex, endIndex));
                }

                if (jsonStr.contains("\"ETH\":")) {
                    int startIndex = jsonStr.indexOf("\"ETH\":") + 6;
                    int endIndex = jsonStr.indexOf(",", startIndex);
                    if (endIndex == -1) endIndex = jsonStr.indexOf("}", startIndex);
                    ethRate = Double.parseDouble(jsonStr.substring(startIndex, endIndex));
                }

                // Calculate conversion
                double result;
                if (fromCrypto.equals("BTC") && toCrypto.equals("ETH")) {
                    result = amount * (ethRate / btcRate);
                } else if (fromCrypto.equals("ETH") && toCrypto.equals("BTC")) {
                    result = amount * (btcRate / ethRate);
                } else {
                    return "Error: Invalid conversion pair. Only BTC-ETH and ETH-BTC conversions are supported.";
                }

                return DECIMAL_FORMAT.format(result);
            } else {
                return "Error: API request failed with status code " + responseCode;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String[] getSupportedCryptos() {
        return new String[]{"BTC", "ETH"};
    }
} 