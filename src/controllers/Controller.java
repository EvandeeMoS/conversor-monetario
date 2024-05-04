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

            String target;
            String base;

            if (option == 1 || option == 2) {
                base = "BRL";
                target = option == 1 ? "USD" : "EUR";
            }
            else if (option == 3 || option == 4) {
                base = "USD";
                target = option == 3 ? "EUR" : "BRL";
            }
            else if (option == 5 || option == 6) {
                base = "EUR";
                target = option == 5 ? "USD" : "BRL";
            }
            else if (option == 7) {
                base = "JPY";
                target = "BRL";
            }
            else if (option == 8) {
                base = "CNY";
                target = "BRL";
            }
            else if (option == 9) {
                base = "RUB";
                target = "BRL";
            }
            else if (option == 10) {
                System.out.println("Por gentileza, insira o código da moeda a ser convertida. (ex: BRL/USD/EUR...)\n");
                System.out.print(": ");
                base = scanner.nextLine();
                System.out.println("Por gentileza, insira o código da moeda alvo da conversão. (ex: BRL/USD/EUR...)\n");
                System.out.print(": ");
                target = scanner.nextLine();
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


                view.displayConversionResult(baseCurrency.getConvertRatios().get(target), base, value, target, conversion);
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
