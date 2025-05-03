package v2.com.currency.converter.controller;

import v2.com.currency.converter.view.CurrencyView;
import v2.com.currency.converter.model.CurrencyModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CurrencyController {
    private CurrencyView view;
    private CurrencyModel model;

    public CurrencyController(CurrencyView view, CurrencyModel model) {
        this.view = view;
        this.model = model;
        populateCurrencyOptions();
        initializeListeners();
        updateRateLabel();
    }

    private void populateCurrencyOptions() {
        view.populateCurrencies(model.getSupportedCurrencies());
    }

    private void initializeListeners() {
        view.getConvertButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConversion();
            }
        });

        ActionListener comboBoxListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRateLabel();
            }
        };
        view.getCurrencyCombo1().addActionListener(comboBoxListener);
        view.getCurrencyCombo2().addActionListener(comboBoxListener);

        view.getRefreshButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.refreshExchangeRate();
                populateCurrencyOptions();
                updateRateLabel();
                view.setResultAmount(0);
                view.getAmountField1().setText("0");
            }
        });

        view.getCloseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getFrame().dispose();
                System.exit(0);
            }
        });

        view.getAmountField1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConversion();
            }
        });
    }

    private void handleConversion() {
        String fromCurrency = view.getSelectedCurrency1();
        String toCurrency = view.getSelectedCurrency2();
        double amount = view.getInputAmount();

        if (fromCurrency == null || toCurrency == null) {
            view.showError("Please select both 'From' and 'To' currencies.");
            return;
        }

        if (Double.isNaN(amount)) {
            return;
        }

        double result = model.convert(amount, fromCurrency, toCurrency);

        if (Double.isNaN(result)) {
            view.showError("Conversion failed. Check if rates are available.");
        }

        view.setResultAmount(result);
        updateRateLabel();
    }

    private void updateRateLabel() {
        String fromCurrency = view.getSelectedCurrency1();
        String toCurrency = view.getSelectedCurrency2();

        if (fromCurrency != null && toCurrency != null) {
            double rate = model.getExchangeRate(fromCurrency, toCurrency);
            view.updateExchangeRateLabel(fromCurrency, toCurrency, rate);
        } else {
            view.updateExchangeRateLabel("?", "?", Double.NaN);
        }
    }
}