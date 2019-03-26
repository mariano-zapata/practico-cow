package com.mercadolibre.router;

import java.util.List;

public class RestEndpointErrorResponse extends RestEndpointResponse {

    private String[] errors;

    public RestEndpointErrorResponse(String status, String message, String... errors) {
        super(status, message);
        this.errors = errors;
    }

    public RestEndpointErrorResponse(String status, String message, List<String> errors) {
        super(status, message);
        this.errors = errors.stream().toArray(String[]::new);
    }
}
