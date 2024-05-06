package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonParser {
    private final Gson gson;
    private final FileHandler fileHandler;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.fileHandler = new FileHandler();
    }

    public Currency jsonToCurrency(String json) {
        try {
            return gson.fromJson(json, Currency.class);
        }
        catch (JsonSyntaxException e) {
            fileHandler.logWrite(e.getMessage());
        }
        return null;
    }
}
