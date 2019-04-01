package com.mercadolibre.business.service;

import com.mercadolibre.business.converter.PaymentConverter;
import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.business.validator.PagoValidator;
import com.mercadolibre.model.Pago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.apache.http.HttpStatus;

import static com.mercadolibre.Constants.STATUS_BAD_REQUEST;

public final class PagoService extends Service {

    private static final PagoService INSTANCE = new PagoService();

    private PagoValidator pagoValidator = new PagoValidator();

    private PaymentConverter paymentConverter = new PaymentConverter();

    private PagoService() {
        if (INSTANCE != null) {
            throw new IllegalStateException("PagoService singleton already created");
        }
    }

    public static PagoService getInstance() {
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone PagoService class");
    }

    public Payment save(Pago pago) throws MPException {
        Errors errors = new Errors();
        pagoValidator.validate(pago, errors);
        if (errors.hasErrors()) {
            throw buildPracticoException(errors, HttpStatus.SC_BAD_REQUEST, STATUS_BAD_REQUEST);
        }

        Payment payment = paymentConverter.convert(pago);
        return payment.save();
    }
}
