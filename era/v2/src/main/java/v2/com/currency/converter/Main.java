package v2.com.currency.converter;
import v2.com.currency.converter.view.CurrencyView;
import v2.com.currency.converter.model.CurrencyModel;
import v2.com.currency.converter.controller.CurrencyController;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CurrencyView view = new CurrencyView();
                CurrencyModel model = new CurrencyModel();
                new CurrencyController(view, model);
                view.show();
            }
        });
    }
}