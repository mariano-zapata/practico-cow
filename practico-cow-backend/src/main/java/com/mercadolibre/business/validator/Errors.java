package com.mercadolibre.business.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Errors {

    private List<String> errors;

    public Errors() {
        errors = new ArrayList<>();
    }

    public Errors(Collection<String> errors) {
        this.errors = new ArrayList<>(errors);
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void appendError(String message) {
        errors.add(message);
    }
}
