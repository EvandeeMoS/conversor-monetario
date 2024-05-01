package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonParser {
    Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public Currency jsonToCurrency(String json) {
        try {
            return gson.fromJson(json, Currency.class);
        }
        catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
