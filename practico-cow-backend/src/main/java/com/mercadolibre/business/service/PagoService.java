package com.mercadolibre.business.service;

import com.mercadolibre.business.converter.PaymentConverter;
import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.business.validator.PagoValidator;
import com.mercadolibre.model.Pago;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.apache.http.HttpStatus;

import static com.mercadolibre.Constants.ACCESS_TOKEN;

public class PagoService extends Service {

    private PagoValidator pagoValidator;

    private PaymentConverter paymentConverter;

    public PagoService() {
        pagoValidator = new PagoValidator();
        paymentConverter = new PaymentConverter();
    }

    public Payment save(Pago pago) throws MPException {
        Errors errors = new Errors();
        pagoValidator.validate(pago, errors);
        if (errors.hasErrors()) {
            throw new MPException(buildErrorMessage(errors), null, HttpStatus.SC_BAD_REQUEST);
        }

        Payment payment = paymentConverter.convert(pago);
        MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
        return payment.save();
    }
}
