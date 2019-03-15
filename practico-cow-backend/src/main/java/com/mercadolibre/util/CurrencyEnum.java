package com.mercadolibre.util;

public enum CurrencyEnum {

    PESO_ARGENTINO("ARS");

    private String id;

    CurrencyEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static CurrencyEnum getById(String id) {
        switch (id) {
            case "ARS":
                return PESO_ARGENTINO;
            default:
                return null;
        }
    }
}
