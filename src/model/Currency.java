package model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Currency {
    @SerializedName("base_code")
    private String baseCode;
    @SerializedName("time_last_update_unix")
    private int lastUpdate;
    @SerializedName("conversion_rates")
    private Map<String, Double> convertRatios;

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Map<String, Double> getConvertRatios() {
        return convertRatios;
    }

    public void setConvertRatios(Map<String, Double> ratios) {
        this.convertRatios = ratios;
    }
}
