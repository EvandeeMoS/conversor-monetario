package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.InterruptedException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;

public class CurrencyDataGetter {
    private String apiKey;
    FileHandler fileHandler;

    public CurrencyDataGetter() {
        fileHandler = new FileHandler();
        try (BufferedReader bfReader = new BufferedReader(new FileReader("enter-your-api-key-here"))) {
            this.apiKey = bfReader.readLine();
        }
        catch (IOException e) {
            fileHandler.logWrite(e.getMessage());
            fileHandler.createApiKeyFile();
        }
    }

    public String getCurrencyData(String currency) {
        String requestUri = "https://v6.exchangerate-api.com/v6/%s/latest/%s"
                .formatted(this.apiKey, currency);
        return fetchData(requestUri);
    }

    public String getCurrencyConversion(String fromCurr, String toCurr) {
        String requestUri = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/1"
                .formatted(this.apiKey, fromCurr, toCurr);
        return fetchData(requestUri);
    }

    public String getCurrencyConversion(String fromCurr, String toCurr, double value) {
        // I concatenated the value instead of just put it inside the formatted because of the default locale,
        // it was changing the dot from the double to a comma, because in my language decimal numbers are
        // separated by commas instead of dots
        String requestUri = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/"
                .formatted(this.apiKey, fromCurr, toCurr) + value + "/";
        System.out.println(requestUri);
        return fetchData(requestUri);
    }

    private String fetchData(String uri) throws IllegalArgumentException {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            }
            else {
                fileHandler.logWrite("""
                        fetch to: %s
                        response:
                          status: %d
                          body: %s""".formatted(uri, response.statusCode(), response.body()));
                if (response.statusCode() == 404) {
                    throw new IllegalArgumentException("O código de moeda é desconhecido. Por gentileza, tente novamente");
                }
                if (response.statusCode() == 403) {
                    throw new Error("Api key inválida para essa requisição");
                }
            }
        }
        catch (IOException | InterruptedException e) {
            fileHandler.logWrite(e.getMessage());
            throw new RuntimeException("Algo de errado ocorreu");
        }
        return null;
    }
}
