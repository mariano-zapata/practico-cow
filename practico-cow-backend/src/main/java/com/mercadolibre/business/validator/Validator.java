package com.mercadolibre.business.validator;

public interface Validator<T> {

    void validate(T source, Errors errors);
}
