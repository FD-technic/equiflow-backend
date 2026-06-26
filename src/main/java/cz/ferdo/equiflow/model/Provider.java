package cz.ferdo.equiflow.model;

public enum Provider {
    ALPHAVANTAGE("av", "Alpha Vantage"),
    YAHOO("yahoo", "Yahoo Finance");

    private final String key;
    private final String value;

    Provider(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
