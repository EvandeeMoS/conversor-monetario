package views;

public class AppView {
    public void displayMenuConversions() {
        System.out.println("""
                +=========================================+
                |           Conversor Monetário           |
                +-----------------------------------------+
                |                                         |
                |  1) Real brasileiro para dólar          |
                |  2) Real brasileiro para Euro           |
                |  3) Dólar para Euro                     |
                |  4) Dólar para real brasileiro          |
                |  5) Euro para dólar                     |
                |  6) Euro para real brasileiro           |
                |  7) Iene para real brasileiro           |
                |  8) Yuan para real brasileiro           |
                |  9) Rublo russo para real brasileiro    |
                |  10) Moedas personalizadas              |
                |                                         |
                |  0) Finalizar o programa                |
                |                                         |
                +-----------------------------------------+
                |  Por gentileza, escolha a conversão     |
                |  que deseja realizar usando o número    |
                |  correspondente.                        |
                +=========================================+
                """);
    }

    public void displayConversionResult(
            double ratio,
            String base,
            double value,
            String target,
            double result) {
        System.out.println("""
                +=========================================+
                |  Resultados                             |
                +=========================================+
                |                                         |
                | A razão da conversão é:                 |""");

        System.out.printf("| 1 [%s] -> %.4f [%s]" + " ".repeat(23 - Double.toString(ratio).length()) + "|%n",
                base, ratio, target);

        System.out.println("""
                |                                         |
                | O seguinte valor:                       |""");

        // , base, ratio, target)

        // this code is to try to adjust the size of the box as the length of values grow,
        // if the length is too high, it converts the values to scientific notation to not break the box

        int baseValueSize = 32 - (String.format("%.2f", value).length());
        int resultSize = 32 - (String.format("%.2f", result).length());
        if ( !(baseValueSize < 0 || resultSize < 0) ) {
            System.out.printf(
                    "|  %.2f [%s] " + " ".repeat(baseValueSize) + "|%n" +
                    "|                                         |%n",
                    value, base
            );
            System.out.println("| Corresponde a:                          |");
            System.out.printf(
                    "|  %.2f [%s] " + " ".repeat(resultSize) + "|%n" +
                    "|                                         |%n",
                    result, target
            );
        }
        else {
            baseValueSize = 32 - (String.format("%.2e", value).length());
            resultSize = 32 - (String.format("%.2e", result).length());
            System.out.printf(
                    "|  %6.2e [%s] " + " ".repeat(baseValueSize) + "|%n" +
                    "|                                         |%n",
                    value, base
            );
            System.out.println("| Corresponde a:                          |");
            System.out.printf(
                    "|  %6.2e [%s] " + " ".repeat(resultSize) + "|%n" +
                    "|                                         |%n",
                    result, target
            );
        }
        System.out.println("+=========================================+");
    }

    public void afterConversionOptionMenu() {
        System.out.println("""
                +=========================================+
                |     O que gostaria de fazer agora?      |
                +-----------------------------------------+
                |                                         |
                |  1) Converter um novo valor;            |
                |  2) Voltar para o menu de conversão     |
                |                                         |
                |  0) Finalizar o programa                |
                |                                         |
                +-----------------------------------------+
                |  Por gentileza, escolha a conversão     |
                |  que deseja realizar usando o número    |
                |  correspondente.                        |
                +=========================================+
                """);
    }

    public void displayMsg(String msg) {
        System.out.println(msg);
    }
}
