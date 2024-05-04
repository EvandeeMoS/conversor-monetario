package controllers;

import model.Currency;
import model.CurrencyDataGetter;
import model.JsonParser;
import views.AppView;

import java.util.Scanner;

public class Controller {

    public void run() {
        CurrencyDataGetter currencyDataGetter = new CurrencyDataGetter();
        JsonParser parser = new JsonParser();
        Scanner scanner = new Scanner(System.in);
        AppView view = new AppView();

        boolean stop = false;
        int option;
        while (!stop) {

            view.displayMenuConversions();

            System.out.print(": ");
            option = Integer.parseInt(scanner.nextLine());

            String target = "";
            String base = "";

            if (option == 1 || option == 2) {
                base = "BRL";
                switch (option) {
                    case 1 -> target = "USD";
                    case 2 -> target = "EUR";
                }
            }
            else if (option == 3 || option == 4) {
                base = "USD";
                switch (option) {
                    case 3 -> target = "EUR";
                    case 4 -> target = "BRL";
                }
            }
            else if (option == 5 || option == 6) {
                base = "EUR";
                switch (option) {
                    case 5 -> target = "USD";
                    case 6 -> target = "BRL";
                }
            }
            else if (option == 0) {
                break;
            }
            else {
                view.displayMsg("Por gentileza, insira um valor válido");
                continue;
            }

            while (!stop) {

                view.displayMsg("Agora insira um valor para conversão\n");

                System.out.print(": ");
                double value = Double.parseDouble(scanner.nextLine());

                Currency baseCurrency = parser.jsonToCurrency(currencyDataGetter.getCurrencyData(base));
                double convertRatio = baseCurrency.getConvertRatios().get(target);
                double conversion = value * convertRatio;


                view.displayConversionResult(base, value, target, conversion);
                view.afterConversionOptionMenu();

                System.out.print(": ");
                option = Integer.parseInt(scanner.nextLine());
                if (option == 2) {
                    break;
                }
                else if (option == 0) {
                    stop = true;
                }
            }
        }
        scanner.close();
        view.displayMsg("Aplicação finalizada");
    }

}
