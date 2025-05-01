package v3.src.main.java.v3.com.crypto.converter.controller;

import v3.src.main.java.v3.com.crypto.converter.model.CryptoModel;
import v3.src.main.java.v3.com.crypto.converter.view.CryptoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CryptoController {
    private CryptoModel model;
    private CryptoView view;

    public CryptoController(CryptoModel model, CryptoView view) {
        this.model = model;
        this.view = view;
        
        // Add action listener to the convert button
        view.addConvertListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });
    }

    private void convert() {
        String fromCrypto = view.getFromCrypto();
        String toCrypto = view.getToCrypto();
        double amount = view.getAmount();

        if (amount <= 0) {
            view.showError("Please enter a valid amount");
            return;
        }

        String result = model.convertCrypto(fromCrypto, toCrypto, amount);
        if (result.startsWith("Error:")) {
            view.showError(result);
        } else {
            view.setResult(result + " " + toCrypto);
        }
    }
} 