package v3.src.main.java.v3.com.crypto.converter.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CryptoView extends JFrame {
    private JComboBox<String> fromCryptoComboBox;
    private JComboBox<String> toCryptoComboBox;
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;

    public CryptoView(String[] supportedCryptos) {
        setTitle("Cryptocurrency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Create components
        fromCryptoComboBox = new JComboBox<>(supportedCryptos);
        toCryptoComboBox = new JComboBox<>(supportedCryptos);
        amountField = new JTextField();
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result will appear here");

        // Add components to frame
        add(new JLabel("From:"));
        add(fromCryptoComboBox);
        add(new JLabel("To:"));
        add(toCryptoComboBox);
        add(new JLabel("Amount:"));
        add(amountField);
        add(new JLabel(""));
        add(convertButton);
        add(new JLabel("Result:"));
        add(resultLabel);

        // Center the window
        setLocationRelativeTo(null);
    }

    public void addConvertListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }

    public String getFromCrypto() {
        return (String) fromCryptoComboBox.getSelectedItem();
    }

    public String getToCrypto() {
        return (String) toCryptoComboBox.getSelectedItem();
    }

    public double getAmount() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public void setResult(String result) {
        resultLabel.setText(result);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
} 