package com.mercadolibre.business.validator;

import com.mercadolibre.model.Preferencia;

import static com.mercadolibre.Constants.*;

public class PreferenciaValidator implements Validator<Preferencia> {

    private PagadorValidator pagadorValidator;

    private ProductoValidator productoValidator;

    public PreferenciaValidator() {
        pagadorValidator = new PagadorValidator();
        productoValidator = new ProductoValidator();
    }

    @Override
    public void validate(Preferencia source, Errors errors) {
        if (source.getPagador() == null) {
            errors.appendError(ERROR_PREFERENCIA_PAGADOR);
            return;
        }

        if (source.getProductos() == null || source.getProductos().isEmpty()) {
            errors.appendError(ERROR_PREFERENCIA_PRODUCTO);
            return;
        }

        pagadorValidator.validate(source.getPagador(), errors);

        source.getProductos().forEach(producto -> productoValidator.validate(producto, errors));
    }
}
