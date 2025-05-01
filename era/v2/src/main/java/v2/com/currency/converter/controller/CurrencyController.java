package v2.com.currency.converter.controller;

import v2.com.currency.converter.view.CurrencyView;
import v2.com.currency.converter.model.CurrencyModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class CurrencyController {
    private CurrencyView view;
    private CurrencyModel model;

    public CurrencyController(CurrencyView view, CurrencyModel model) {
        this.view = view;
        this.model = model;
        initializeListeners();
        updateExchangeRateDisplay();
    }

    private void initializeListeners() {
        // INR to Dollar conversion
        view.getInrButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double inr = Double.parseDouble(view.getInrField().getText());
                    double dollar = model.convertINRToDollar(inr);
                    view.getDollarField().setText(String.format("%.2f", dollar));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view.getFrame(),
                        "Please enter a valid number for INR",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Dollar to INR conversion
        view.getDollarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double dollar = Double.parseDouble(view.getDollarField().getText());
                    double inr = model.convertDollarToINR(dollar);
                    view.getInrField().setText(String.format("%.2f", inr));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view.getFrame(),
                        "Please enter a valid number for USD",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Refresh button
        view.getRefreshButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.refreshExchangeRate();
                updateExchangeRateDisplay();
            }
        });

        // Close button
        view.getCloseButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getFrame().dispose();
            }
        });

        // Window close
        view.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void updateExchangeRateDisplay() {
        // This will be implemented in the model class
        // For now, we'll just show a loading message
        view.updateExchangeRate(model.convertDollarToINR(1));
    }
} 