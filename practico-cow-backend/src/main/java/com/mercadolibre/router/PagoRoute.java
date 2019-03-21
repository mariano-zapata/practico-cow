package com.mercadolibre.router;

import com.google.common.net.MediaType;
import com.mercadolibre.business.service.PagoService;
import com.mercadolibre.model.Pago;
import com.mercadolibre.util.VelocityUtil;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.util.HashMap;

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
        Pago pago = new Pago();
        pago.setMonto(500F).setToken(request.queryParams("token")).setDescripcion("Descripcion")
                .setCuotas(Integer.valueOf(request.queryParams("installments")))
                .setMetodoPagoId(request.queryParams("paymentMethodId")).setEmailPagador(request.queryParams("email"));

        PagoService pagoService = new PagoService();
        Payment payment = pagoService.saveV1(pago);

        response.header(HEADER_CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        response.status(HttpStatus.SC_OK);
        String responseMessage = String.format(PAGO_PROCESADO, payment.getStatus().name(), payment.getStatusDetail());
        RestEndpointResponse endpointResponse = new RestEndpointResponse(STATUS_OK, responseMessage);
        return endpointResponse;
    }

    public static Object getRecibirPagoView(Request request, Response response) {
        return VelocityUtil.render(new HashMap<String, Object>(), TEMPLATE_EJERCICIO_3);
    }
}
