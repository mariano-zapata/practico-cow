package com.mercadolibre.business.validator;

import com.mercadolibre.model.Producto;
import com.mercadolibre.util.CurrencyEnum;
import org.apache.commons.lang3.StringUtils;

import static com.mercadolibre.Constants.*;

public class ProductoValidator implements Validator<Producto> {

    @Override
    public void validate(Producto source, Errors errors) {
        if (StringUtils.isEmpty(source.getId())) {
            errors.appendError(ERROR_PRODUCTO_ID);
        }

        if (source.getCantidad() == null || source.getCantidad() <= 0) {
            errors.appendError(ERROR_PRODUCTO_CANTIDAD);
        }

        if (StringUtils.isEmpty(source.getMoneda()) || CurrencyEnum.getById(source.getMoneda()) == null) {
            errors.appendError(ERROR_PRODUCTO_MONEDA);
        }

        if (source.getPrecio() == null) {
            errors.appendError(ERROR_PRODUCTO_PRECIO);
        }
    }
}
