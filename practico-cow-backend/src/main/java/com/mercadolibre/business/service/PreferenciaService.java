package com.mercadolibre.business.service;

import com.mercadolibre.business.converter.PreferenceConverter;
import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.business.validator.PreferenciaValidator;
import com.mercadolibre.model.Preferencia;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.apache.http.HttpStatus;

import static com.mercadolibre.Constants.STATUS_BAD_REQUEST;

public final class PreferenciaService extends Service {

    private static final PreferenciaService INSTANCE = new PreferenciaService();

    private PreferenciaValidator preferenciaValidator = new PreferenciaValidator();

    private PreferenceConverter preferenceConverter = new PreferenceConverter();

    private PreferenciaService() {
        if (INSTANCE != null) {
            throw new IllegalStateException("PreferenciaService singleton already created");
        }
    }

    public static PreferenciaService getInstance() {
        return  INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone PreferenciaService class");
    }

    public Preference save(Preferencia preferencia) throws MPException {
        Errors errors = new Errors();
        preferenciaValidator.validate(preferencia, errors);
        if (errors.hasErrors()) {
            throw buildPracticoException(errors, HttpStatus.SC_BAD_REQUEST, STATUS_BAD_REQUEST);
        }

        Preference preference = preferenceConverter.convert(preferencia);
        return preference.save();
    }
}
