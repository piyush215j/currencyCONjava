package v3.src.main.java.v3.com.crypto.converter;

import v3.src.main.java.v3.com.crypto.converter.model.CryptoModel;
import v3.src.main.java.v3.com.crypto.converter.view.CryptoView;
import v3.src.main.java.v3.com.crypto.converter.controller.CryptoController;

public class Main {
    public static void main(String[] args) {
        // Create model
        CryptoModel model = new CryptoModel();
        
        // Create view with supported cryptocurrencies
        CryptoView view = new CryptoView(model.getSupportedCryptos());
        
        // Create controller
        new CryptoController(model, view);
        
        // Show the view
        view.setVisible(true);
    }
} 