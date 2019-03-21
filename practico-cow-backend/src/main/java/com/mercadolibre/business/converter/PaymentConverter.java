package com.mercadolibre.business.converter;

import com.mercadolibre.model.Pago;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;

public class PaymentConverter implements Converter<Pago, Payment> {

    @Override
    public Payment convert(Pago source) {
        Payment payment = new Payment();
        payment.setTransactionAmount(source.getMonto()).setToken(source.getToken())
                .setDescription(source.getDescripcion()).setInstallments(source.getCuotas())
                .setPaymentMethodId(source.getMetodoPagoId())
                .setPayer(new Payer().setEmail(source.getEmailPagador()));
        return payment;
    }
}
