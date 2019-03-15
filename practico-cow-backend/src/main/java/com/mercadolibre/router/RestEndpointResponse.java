package com.mercadolibre.router;

import java.io.Serializable;

public class RestEndpointResponse implements Serializable {

    private String status;

    private String message;

    public RestEndpointResponse() {
        super();
    }

    public RestEndpointResponse(String status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
