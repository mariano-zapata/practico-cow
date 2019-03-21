package com.mercadolibre.business.validator;

import com.mercadolibre.model.Pago;
import org.apache.commons.lang3.StringUtils;

import static com.mercadolibre.Constants.*;

public class PagoValidator implements Validator<Pago> {

    @Override
    public void validate(Pago source, Errors errors) {
        if (source.getMonto() == null || source.getMonto() <= 0F) {
            errors.appendError(ERROR_PAGO_MONTO);
        }

        if (StringUtils.isEmpty(source.getToken())) {
            errors.appendError(ERROR_PAGO_TOKEN);
        }

        if (source.getCuotas() == null || source.getCuotas() <= 0) {
            errors.appendError(ERROR_PAGO_CUOTAS);
        }

        if (StringUtils.isEmpty(source.getMetodoPagoId())) {
            errors.appendError(ERROR_PAGO_METODO);
        }

        if (StringUtils.isEmpty(source.getEmailPagador())) {
            errors.appendError(ERROR_PAGO_EMAIL);
        }
    }
}
