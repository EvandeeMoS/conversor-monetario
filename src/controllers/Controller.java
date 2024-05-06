package controllers;

import model.*;
import view.AppView;

import java.util.Scanner;

public class Controller {

    public void run() {
        CurrencyDataGetter currencyDataGetter = new CurrencyDataGetter();
        JsonParser parser = new JsonParser();
        CurrencyCalculator currCalc = new CurrencyCalculator();
        AppView view = new AppView();
        FileHandler fileHandler = new FileHandler();
        Scanner scanner = new Scanner(System.in);

        boolean stop = false;
        int option;
        while (!stop) {
            String target;
            String base;

            try {
                view.displayMenuConversions();

                System.out.print(": ");
                option = Integer.parseInt(scanner.nextLine());
                fileHandler.logWrite("User input: " + option);


                if (option == 1 || option == 2) {
                    base = "BRL";
                    target = option == 1 ? "USD" : "EUR";
                } else if (option == 3 || option == 4) {
                    base = "USD";
                    target = option == 3 ? "EUR" : "BRL";
                } else if (option == 5 || option == 6) {
                    base = "EUR";
                    target = option == 5 ? "USD" : "BRL";
                } else if (option == 7) {
                    base = "JPY";
                    target = "BRL";
                } else if (option == 8) {
                    base = "CNY";
                    target = "BRL";
                } else if (option == 9) {
                    base = "RUB";
                    target = "BRL";
                } else if (option == 10) {
                    System.out.println("Por gentileza, insira o código da moeda a ser convertida. (ex: BRL/USD/EUR...)\n");
                    System.out.print(": ");
                    base = scanner.nextLine().toUpperCase();
                    System.out.println("Por gentileza, insira o código da moeda alvo da conversão. (ex: BRL/USD/EUR...)\n");
                    System.out.print(": ");
                    target = scanner.nextLine().toUpperCase();
                } else if (option == 11) {
                    try {
                        view.displayMsg(fileHandler.historyRead());
                    } catch (RuntimeException e) {
                        view.displayMsg(e.getMessage());
                    }
                    continue;
                } else if (option == 0) {
                    break;
                } else {
                    view.displayMsg("Por gentileza, insira um valor válido");
                    continue;
                }
            }
            catch (NumberFormatException e) {
                fileHandler.logWrite(e.getMessage());
                view.displayMsg("Por gentileza, insira um valor válido");
                continue;
            }

            try {
                fileHandler.logWrite("base selected: " + base + ", target selected: " + target);
                String requestResult = currencyDataGetter.getCurrencyData(base);
                Currency baseCurrency = parser.jsonToCurrency(requestResult);

                while (!stop) {
                    try {
                        view.displayMsg("Agora, insira um valor para conversão\n");

                        System.out.print(": ");
                        double value = Double.parseDouble(scanner.nextLine());

                        double convertRatio = baseCurrency.getConvertRatios().get(target.toUpperCase());
                        double conversion = currCalc.currencyConvertTo(value, convertRatio);

                        fileHandler.historyWrite(base, value, target, conversion);
                        view.displayConversionResult(convertRatio, base, value, target, conversion);
                    }
                    catch (NumberFormatException e) {
                        fileHandler.logWrite(e.getMessage());
                        view.displayMsg("Valor inválido, tente novamente.");
                        continue;
                    }

                    try {
                        view.afterConversionOptionMenu();

                        System.out.print(": ");
                        option = Integer.parseInt(scanner.nextLine());
                        fileHandler.logWrite("User input: " + option);
                        if (option == 2) {
                            break;
                        } else if (option == 0) {
                            stop = true;
                        }
                        else {
                            view.displayMsg("Opção inválida, voltando para o menu...");
                            break;
                        }
                    }
                    catch (NumberFormatException e) {
                        fileHandler.logWrite(e.getMessage());
                        view.displayMsg("Opção inválida, voltando para o menu...");
                        break;
                    }
                }
            }
            catch (IllegalArgumentException e) {
                fileHandler.logWrite(e.getMessage());
                view.displayMsg(e.getMessage());
            }
        }
        String msg = "Aplicação finalizada";
        fileHandler.logWrite(msg);
        view.displayMsg(msg);
        scanner.close();
    }

}
