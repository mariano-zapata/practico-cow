package com.mercadolibre.business.service;

import com.mercadolibre.business.converter.PreferenceConverter;
import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.business.validator.PreferenciaValidator;
import com.mercadolibre.model.Preferencia;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.apache.http.HttpStatus;

import static com.mercadolibre.Constants.CLIENT_ID;
import static com.mercadolibre.Constants.CLIENT_SECRET;

public class PreferenciaService {

    public Preference save(Preferencia preferencia) throws MPException {
        Errors errors = new Errors();
        PreferenciaValidator preferenciaValidator = new PreferenciaValidator();
        preferenciaValidator.validate(preferencia, errors);
        if (errors.hasErrors()) {
            throw new MPException(buildErrorMessage(errors), null, HttpStatus.SC_BAD_REQUEST);
        }

        PreferenceConverter preferenceConverter = new PreferenceConverter();
        Preference preference = preferenceConverter.convert(preferencia);
        MercadoPago.SDK.setClientId(CLIENT_ID);
        MercadoPago.SDK.setClientSecret(CLIENT_SECRET);
        return preference.save();
    }

    private String buildErrorMessage(Errors errors) {
        StringBuilder message = new StringBuilder();
        errors.getErrors().forEach(error -> message.append(error).append(". - "));
        return message.toString();
    }
}
