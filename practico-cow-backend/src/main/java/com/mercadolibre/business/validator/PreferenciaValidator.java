package com.mercadolibre.business.validator;

import com.mercadolibre.model.Preferencia;

import static com.mercadolibre.Constants.*;

public class PreferenciaValidator implements Validator<Preferencia> {

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

        PagadorValidator pagadorValidator = new PagadorValidator();
        pagadorValidator.validate(source.getPagador(), errors);

        ProductoValidator productoValidator = new ProductoValidator();
        source.getProductos().forEach(producto -> productoValidator.validate(producto, errors));
    }
}
