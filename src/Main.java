import model.CurrencyDataGetter;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        CurrencyDataGetter currencyDataGetter = new CurrencyDataGetter();

        System.out.println(Locale.getDefault());
        System.out.println(currencyDataGetter.getCurrencyData("usd"));
        System.out.println(currencyDataGetter.getCurrencyConversion("usd", "BRL"));
        System.out.println(currencyDataGetter.getCurrencyConversion("usd", "BRL", 20));
    }
}