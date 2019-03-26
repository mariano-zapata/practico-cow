package com.mercadolibre.business.service;

import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.util.PracticoException;

public abstract class Service {

    protected PracticoException buildPracticoException(Errors errors, int statusCode, String statusMessage) {
        return new PracticoException(statusMessage, errors.getErrors(), statusCode, null);
    }
}
