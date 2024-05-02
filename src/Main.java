import model.CurrencyDataGetter;
import model.JsonParser;
import model.Currency;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyDataGetter currencyDataGetter = new CurrencyDataGetter();
        JsonParser parser = new JsonParser();

        Scanner input = new Scanner(System.in);

        int opt = 1;
        double value;

        while (opt != 0) {
            System.out.println("""
                =========================================
                           Conversor Monetário
                -----------------------------------------
                
                  1) Real brasileiro para dólar
                  2) Real brasileiro para Euro
                  3) Dólar para Euro
                  4) Dólar para real brasileiro
                  5) Euro para dólar
                  6) Euro para real brasileiro
                
                  0) Finalizar o programa
                
                -----------------------------------------
                  Por gentileza, escolha a conversão
                  que deseja realizar usando o número
                  correspondente.
                =========================================
                """);

            System.out.print(": ");
            opt = Integer.parseInt(input.nextLine());

            String target = "";
            String base = "";

            if (opt == 1 || opt == 2) {
                base = "BRL";
                switch (opt) {
                    case 1 -> target = "USD";
                    case 2 -> target = "EUR";
                }
            }
            else if (opt == 3 || opt == 4) {
                base = "USD";
                switch (opt) {
                    case 3 -> target = "EUR";
                    case 4 -> target = "BRL";
                }
            }
            else if (opt == 5 || opt == 6) {
                base = "EUR";
                switch (opt) {
                    case 5 -> target = "USD";
                    case 6 -> target = "BRL";
                }
            }
            else if (opt == 0) {
                break;
            }
            else {
                System.out.println("Por gentileza, insira um valor válido");
                continue;
            }

            while (true) {

                System.out.println("Agora insira um valor para conversão\n");

                System.out.print(": ");
                value = Double.parseDouble(input.nextLine());

                Currency baseCurrency = parser.jsonToCurrency(currencyDataGetter.getCurrencyData(base));
                double conversion = baseCurrency.getConvertRatios().get(target) * value;

                System.out.printf("O valor %.2f [%s] é correspondente a %.2f [%s]%n%n",
                        value, base, conversion, target);

                System.out.println("""
                =========================================
                     O que gostaria de fazer agora?
                -----------------------------------------
                
                  1) Converter um novo valor;
                  2) Voltar para o menu de conversão
                
                  0) Finalizar o programa
                
                -----------------------------------------
                  Por gentileza, escolha a conversão
                  que deseja realizar usando o número
                  correspondente.
                =========================================
                """);

                System.out.print(": ");
                opt = Integer.parseInt(input.nextLine());
                if (opt == 2) {
                    break;
                }
                else if (opt == 0) {
                    break;
                }
            }
            System.out.println("Aplicação finalizada");
        }
    }
}