package com.mercadolibre.util;

import com.mercadopago.exceptions.MPException;

import java.util.List;

public class PracticoException extends MPException {

    private String statusMessage;

    private List<String> errors;

    public PracticoException(String statusMessage, List<String> errors, Integer statusCode, String requestId) {
        super(null, requestId, statusCode);
        this.statusMessage = statusMessage;
        this.errors = errors;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public List<String> getErrors() {
        return errors;
    }
}
