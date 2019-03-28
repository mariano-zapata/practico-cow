package com.mercadolibre.router;

import com.google.common.net.MediaType;
import com.mercadolibre.business.service.PagoService;
import com.mercadolibre.model.Pago;
import com.mercadolibre.util.VelocityUtil;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static com.mercadolibre.Constants.*;

public class PagoRoute {

    public static Object procesarPago(Request request, Response response) throws MPException {
        Pago pago = new Pago();
        pago.setMonto(100F).setToken(request.queryParams("token")).setDescripcion("Descripcion")
                .setCuotas(Integer.valueOf(request.queryParams("installments")))
                .setMetodoPagoId(request.queryParams("payment_method_id"))
                .setIssuerId(request.queryParams("issuer_id"))
                .setEmailPagador("test_user_19653727@testuser.com");

        PagoService pagoService = new PagoService();
        Payment payment = pagoService.save(pago);

        response.header(HEADER_CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        response.status(HttpStatus.SC_OK);
        String responseMessage = String.format(PAGO_PROCESADO, payment.getStatus().name(), payment.getStatusDetail());
        RestEndpointResponse endpointResponse = new RestEndpointResponse(STATUS_OK, responseMessage);
        return endpointResponse;
    }

    public static Object recibirPago(Request request, Response response) throws MPException {
        Pago pago = new Pago();
        pago.setMonto(500F).setToken(request.queryParams("token")).setDescripcion("Descripcion")
                .setCuotas(Integer.valueOf(request.queryParams("installments")))
                .setMetodoPagoId(request.queryParams("paymentMethodId")).setEmailPagador(request.queryParams("email"));

        PagoService pagoService = new PagoService();
        Payment payment = pagoService.save(pago);

        response.header(HEADER_CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        response.status(HttpStatus.SC_OK);
        String responseMessage = String.format(PAGO_PROCESADO, payment.getStatus().name(), payment.getStatusDetail());
        RestEndpointResponse endpointResponse = new RestEndpointResponse(STATUS_OK, responseMessage);
        return endpointResponse;
    }

    public static Object estadoPago(Request request, Response response) {
        response.header(HEADER_CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        response.status(HttpStatus.SC_OK);
        String responseMessage = String.format(PAGO_ESTADO, request.queryParams("payment_id"),
                request.queryParams("payment_status"), request.queryParams("payment_status_detail"));
        RestEndpointResponse endpointResponse = new RestEndpointResponse(STATUS_OK, responseMessage);
        return endpointResponse;
    }

    public static Object getRecibirPagoView(Request request, Response response) {
        return VelocityUtil.render(new HashMap<String, Object>(), TEMPLATE_EJERCICIO_3);
    }

    public static Object getProcesarPagoView(Request request, Response response) {
        return VelocityUtil.render(new HashMap<String, Object>(), TEMPLATE_EJERCICIO_4);
    }

    public static Object getEstadoPagoView(Request request, Response response) throws MPException {
        Item item = new Item();
        item.setId("1234").setTitle("Product").setQuantity(1).setCurrencyId("ARS").setUnitPrice(95.7F);

        Payer payer = new Payer().setEmail("test_user_61906920@testuser.com");

        Preference preference = new Preference().setPayer(payer).appendItem(item).save();

        Map<String, Object> model = new HashMap<>();
        model.put("preferenceId", preference.getId());
        return VelocityUtil.render(model, TEMPLATE_EJERCICIO_5);
    }
}
