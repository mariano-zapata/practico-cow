package com.mercadolibre.business.service;

import com.mercadolibre.business.converter.PreferenceConverter;
import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.business.validator.PreferenciaValidator;
import com.mercadolibre.model.Preferencia;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.apache.http.HttpStatus;

import static com.mercadolibre.Constants.STATUS_BAD_REQUEST;
import static com.mercadolibre.Constants.CLIENT_ID;
import static com.mercadolibre.Constants.CLIENT_SECRET;

public class PreferenciaService extends Service {

    private PreferenciaValidator preferenciaValidator;

    private PreferenceConverter preferenceConverter;

    public PreferenciaService() {
        preferenciaValidator = new PreferenciaValidator();
        preferenceConverter = new PreferenceConverter();
    }

    public Preference save(Preferencia preferencia) throws MPException {
        Errors errors = new Errors();
        preferenciaValidator.validate(preferencia, errors);
        if (errors.hasErrors()) {
            throw buildPracticoException(errors, HttpStatus.SC_BAD_REQUEST, STATUS_BAD_REQUEST);
        }

        Preference preference = preferenceConverter.convert(preferencia);
        MercadoPago.SDK.setClientId(CLIENT_ID);
        MercadoPago.SDK.setClientSecret(CLIENT_SECRET);
        return preference.save();
    }
}
