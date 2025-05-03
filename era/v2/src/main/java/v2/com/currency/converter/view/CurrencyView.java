package v2.com.currency.converter.view;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Set;

public class CurrencyView {
    private JFrame frame;
    private JTextField amountField1;
    private JTextField amountField2;
    private JComboBox<String> currencyCombo1;
    private JComboBox<String> currencyCombo2;
    private JButton convertButton;
    private JButton closeButton;
    private JButton refreshButton;
    private JLabel rateLabel;
    private DecimalFormat decimalFormat;

    public CurrencyView() {
        decimalFormat = new DecimalFormat("#,##0.00##");
        initialize();
    }

    private void initialize() {
        frame = new JFrame("CURRENCY CONVERTER V2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 350);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(30, 40, 50, 30);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(280, 40, 30, 30);

        currencyCombo1 = new JComboBox<>();
        currencyCombo1.setBounds(80, 40, 100, 30);

        currencyCombo2 = new JComboBox<>();
        currencyCombo2.setBounds(310, 40, 100, 30);

        JLabel amount1Label = new JLabel("Amount:");
        amount1Label.setBounds(30, 90, 60, 30);

        JLabel amount2Label = new JLabel("Result:");
        amount2Label.setBounds(280, 90, 60, 30);

        amountField1 = new JTextField("0");
        amountField1.setBounds(90, 90, 120, 30);
        amountField1.setHorizontalAlignment(JTextField.RIGHT);

        amountField2 = new JTextField("0");
        amountField2.setBounds(340, 90, 120, 30);
        amountField2.setHorizontalAlignment(JTextField.RIGHT);
        amountField2.setEditable(false);

        convertButton = new JButton("Convert");
        convertButton.setBounds(200, 140, 100, 30);

        refreshButton = new JButton("Refresh Rates");
        refreshButton.setBounds(100, 190, 140, 30);

        closeButton = new JButton("Close");
        closeButton.setBounds(280, 190, 100, 30);

        rateLabel = new JLabel("Select currencies to see rate");
        rateLabel.setBounds(50, 240, 400, 30);
        rateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(fromLabel);
        frame.add(currencyCombo1);
        frame.add(toLabel);
        frame.add(currencyCombo2);
        frame.add(amount1Label);
        frame.add(amountField1);
        frame.add(amount2Label);
        frame.add(amountField2);
        frame.add(convertButton);
        frame.add(refreshButton);
        frame.add(closeButton);
        frame.add(rateLabel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void updateExchangeRateLabel(String fromCurrency, String toCurrency, double rate) {
        if (Double.isNaN(rate)) {
             rateLabel.setText("Rate not available for " + fromCurrency + " to " + toCurrency);
        } else {
            rateLabel.setText(String.format("Rate: 1 %s = %s %s", fromCurrency, decimalFormat.format(rate), toCurrency));
        }
    }

    public void populateCurrencies(Set<String> currencies) {
        String selected1 = (String) currencyCombo1.getSelectedItem();
        String selected2 = (String) currencyCombo2.getSelectedItem();

        currencyCombo1.removeAllItems();
        currencyCombo2.removeAllItems();
        String[] currencyArray = currencies.toArray(new String[0]);
        java.util.Arrays.sort(currencyArray);
        for (String currency : currencyArray) {
            currencyCombo1.addItem(currency);
            currencyCombo2.addItem(currency);
        }

        if (selected1 != null && currencies.contains(selected1)) {
            currencyCombo1.setSelectedItem(selected1);
        } else if (currencies.contains("USD")) {
             currencyCombo1.setSelectedItem("USD");
        }

        if (selected2 != null && currencies.contains(selected2)) {
             currencyCombo2.setSelectedItem(selected2);
        } else if (currencies.contains("INR")) {
             currencyCombo2.setSelectedItem("INR");
        }
    }

    public JComboBox<String> getCurrencyCombo1() {
        return currencyCombo1;
    }

    public JComboBox<String> getCurrencyCombo2() {
        return currencyCombo2;
    }

    public JTextField getAmountField1() {
        return amountField1;
    }

    public JTextField getAmountField2() {
        return amountField2;
    }

    public JButton getConvertButton() {
        return convertButton;
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

    public String getSelectedCurrency1() {
        return (String) currencyCombo1.getSelectedItem();
    }

    public String getSelectedCurrency2() {
        return (String) currencyCombo2.getSelectedItem();
    }

    public void setResultAmount(double amount) {
        if (Double.isNaN(amount)) {
            amountField2.setText("Error");
        } else {
            amountField2.setText(decimalFormat.format(amount));
        }
    }

    public double getInputAmount() {
        try {
            String text = amountField1.getText().replace(",", "");
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return Double.NaN;
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
} 