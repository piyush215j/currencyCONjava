package com.currency.converter.controller;

import com.currency.converter.view.CurrencyView;
import com.currency.converter.model.CurrencyModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CurrencyController {
    private CurrencyView view;

    public CurrencyController(CurrencyView view) {
        this.view = view;
        initializeListeners();
    }

    private void initializeListeners() {
        // INR to Dollar conversion
        view.getInrButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double inr = Double.parseDouble(view.getInrField().getText());
                double dollar = CurrencyModel.convertINRToDollar(inr);
                view.getDollarField().setText(String.valueOf(dollar));
            }
        });

        // Dollar to INR conversion
        view.getDollarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double dollar = Double.parseDouble(view.getDollarField().getText());
                double inr = CurrencyModel.convertDollarToINR(dollar);
                view.getInrField().setText(String.valueOf(inr));
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
} 