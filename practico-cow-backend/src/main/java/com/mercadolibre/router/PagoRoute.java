package com.mercadolibre.router;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import spark.Request;
import spark.Response;

import static com.mercadolibre.Constants.*;

public class PagoRoute {

    public static Object procesarPago(Request request, Response response) throws MPException {
        MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
        Payment payment = new Payment();
        payment.setTransactionAmount(100F).setToken(request.queryParams("token"))
                .setDescription("Descripcion")
                .setInstallments(Integer.valueOf(request.queryParams("installments")))
                .setPaymentMethodId(request.queryParams("payment_method_id"))
                .setIssuerId(request.queryParams("issuer_id"))
                .setPayer(new com.mercadopago.resources.datastructures.payment.Payer()
                        .setEmail("test_user_19653727@testuser.com"));
        payment.save();
        return payment;
    }

    public static Object recibirPago(Request request, Response response) throws MPException {
        MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
        Payment payment = new Payment();
        payment.setTransactionAmount(174F).setToken(request.queryParams("token"))
                .setDescription("Descripcion").setInstallments(Integer.valueOf(request.queryParams("installments")))
                .setPaymentMethodId(request.queryParams("paymentMethodId"))
                .setPayer(new com.mercadopago.resources.datastructures.payment.Payer()
                        .setEmail(request.queryParams("email")));
        payment.save();
        return payment;
    }
}
