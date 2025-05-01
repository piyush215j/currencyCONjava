package com.currency.converter.view;

import javax.swing.*;
import java.awt.*;
import com.currency.converter.model.CurrencyModel;

public class CurrencyView {
    private JFrame frame;
    private JTextField inrField;
    private JTextField dollarField;
    private JButton inrButton;
    private JButton dollarButton;
    private JButton closeButton;

    public CurrencyView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("CURRENCY CONVERTER");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(false); // Disable maximize button

        // Labels
        JLabel inrLabel = new JLabel("Rupees:");
        inrLabel.setBounds(20, 40, 60, 30);
        
        JLabel dollarLabel = new JLabel("Dollars:");
        dollarLabel.setBounds(170, 40, 60, 30);

        // Text Fields
        inrField = new JTextField("0");
        inrField.setBounds(80, 40, 50, 30);
        
        dollarField = new JTextField("0");
        dollarField.setBounds(240, 40, 50, 30);

        // Buttons with increased size
        inrButton = new JButton("INR");
        inrButton.setBounds(50, 80, 80, 30); // Increased size
        
        dollarButton = new JButton("Dollar");
        dollarButton.setBounds(190, 80, 80, 30); // Increased size
        
        closeButton = new JButton("close");
        closeButton.setBounds(150, 150, 80, 30); // Increased size

        // Add components to frame
        frame.add(inrLabel);
        frame.add(inrField);
        frame.add(dollarLabel);
        frame.add(dollarField);
        frame.add(inrButton);
        frame.add(dollarButton);
        frame.add(closeButton);
    }

    public void show() {
        frame.setVisible(true);
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

    public JFrame getFrame() {
        return frame;
    }
} 