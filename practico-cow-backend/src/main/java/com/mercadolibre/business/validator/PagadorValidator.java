package com.mercadolibre.business.validator;

import com.mercadolibre.model.Pagador;
import org.apache.commons.lang3.StringUtils;

import static com.mercadolibre.Constants.*;

public class PagadorValidator implements Validator<Pagador> {

    @Override
    public void validate(Pagador source, Errors errors) {
        if (StringUtils.isEmpty(source.getApellido())) {
            errors.appendError(ERROR_PAGADOR_APELLIDO);
        }

        if (StringUtils.isEmpty(source.getNombre())) {
            errors.appendError(ERROR_PAGADOR_NOMBRE);
        }

        if (StringUtils.isEmpty(source.getEmail())) {
            errors.appendError(ERROR_PAGADOR_EMAIL);
        }
    }
}
