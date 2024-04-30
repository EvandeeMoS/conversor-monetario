package model;

import java.lang.InterruptedException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;

public class CurrencyDataGetter {
    private final String apiKey;

    public CurrencyDataGetter() {
        this.apiKey = "0b419e60e22651c189d4c2bd";
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
        String requestUri = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/"
                .formatted(this.apiKey, fromCurr, toCurr) + value + "/";
        System.out.println(requestUri);
        return fetchData(requestUri);
    }

    private String fetchData(String uri) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return response.body();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
