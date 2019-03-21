package com.mercadolibre.business.service;

import com.mercadolibre.business.validator.Errors;

public abstract class Service {

    protected String buildErrorMessage(Errors errors) {
        StringBuilder message = new StringBuilder();
        errors.getErrors().forEach(error -> message.append(error).append(". - "));
        return message.toString();
    }
}
