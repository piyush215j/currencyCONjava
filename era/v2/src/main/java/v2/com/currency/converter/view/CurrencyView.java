package v2.com.currency.converter.view;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CurrencyView {
    private JFrame frame;
    private JTextField inrField;
    private JTextField dollarField;
    private JButton inrButton;
    private JButton dollarButton;
    private JButton closeButton;
    private JButton refreshButton;
    private JLabel rateLabel;
    private DecimalFormat decimalFormat;

    public CurrencyView() {
        decimalFormat = new DecimalFormat("#,##0.00");
        initialize();
    }

    private void initialize() {
        frame = new JFrame("CURRENCY CONVERTER");
        frame.setSize(500, 350);
        frame.setLayout(null);
        frame.setResizable(false);

        // Labels
        JLabel inrLabel = new JLabel("Rupees (₹):");
        inrLabel.setBounds(20, 40, 80, 30);
        
        JLabel dollarLabel = new JLabel("Dollars ($):");
        dollarLabel.setBounds(170, 40, 80, 30);

        // Text Fields
        inrField = new JTextField("0");
        inrField.setBounds(100, 40, 100, 30);
        
        dollarField = new JTextField("0");
        dollarField.setBounds(250, 40, 100, 30);

        // Buttons
        inrButton = new JButton("INR → USD");
        inrButton.setBounds(50, 90, 120, 30);
        
        dollarButton = new JButton("USD → INR");
        dollarButton.setBounds(190, 90, 120, 30);
        
        refreshButton = new JButton("Refresh Rate");
        refreshButton.setBounds(150, 140, 120, 30);
        
        closeButton = new JButton("Close");
        closeButton.setBounds(150, 190, 120, 30);

        // Exchange Rate Label
        rateLabel = new JLabel("Current Rate: Loading...");
        rateLabel.setBounds(150, 240, 200, 30);
        rateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components to frame
        frame.add(inrLabel);
        frame.add(inrField);
        frame.add(dollarLabel);
        frame.add(dollarField);
        frame.add(inrButton);
        frame.add(dollarButton);
        frame.add(refreshButton);
        frame.add(closeButton);
        frame.add(rateLabel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void updateExchangeRate(double rate) {
        rateLabel.setText("Current Rate: 1 USD = " + decimalFormat.format(rate) + " INR");
    }

    public JTextField getInrField() {
        return inrField;
    }

    public JTextField getDollarField() {
        return dollarField;
    }

    public JButton getInrButton() {
        return inrButton;
    }

    public JButton getDollarButton() {
        return dollarButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JFrame getFrame() {
        return frame;
    }
} 