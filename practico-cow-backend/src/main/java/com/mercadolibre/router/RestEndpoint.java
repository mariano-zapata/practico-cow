package com.mercadolibre.router;

import com.mercadolibre.Utils;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Address;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Phone;
import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

public class RestEndpoint {

    public void init() {
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get("/practico/preferencia", RestEndpoint::crearPreferencia, Utils.json());
        post("/practico/pago", RestEndpoint::recibirPago, Utils.json());
        post("/practico/procesar-pago", RestEndpoint::procesarPago);
    }

    private static Object procesarPago(Request request, Response response) throws MPException {
        MercadoPago.SDK.setAccessToken("TEST-3171675646578969-022708-663fcd89e9e29cf79f1a87632c13284e-403574962");
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

    private static Object recibirPago(Request request, Response response) throws MPException {
        MercadoPago.SDK.setAccessToken("TEST-3171675646578969-022708-663fcd89e9e29cf79f1a87632c13284e-403574962");
        Payment payment = new Payment();
        payment.setTransactionAmount(174F).setToken(request.queryParams("token"))
        .setDescription("Descripcion").setInstallments(Integer.valueOf(request.queryParams("installments")))
                .setPaymentMethodId(request.queryParams("paymentMethodId"))
        .setPayer(new com.mercadopago.resources.datastructures.payment.Payer()
        .setEmail(request.queryParams("email")));
        payment.save();
        return payment;
    }

    private static Object crearPreferencia(Request request, Response response) throws MPException {
        MercadoPago.SDK.setClientId("3171675646578969");
        MercadoPago.SDK.setClientSecret("ju7qd9Ec2XR3aGNcxMFbV0JMzsxK1s3p");

        Item item = new Item();
        item.setId("1234").setTitle("Product").setQuantity(1).setCurrencyId("ARS").setUnitPrice(95.7F);

        Payer payer = new Payer();
        payer.setName("Carlos").setSurname("Ocasio").setEmail("test_user_58877546@testuser.com")
                .setDateCreated("2018-06-02T12:58:41.425-04:00")
                .setPhone(new Phone().setAreaCode("343").setNumber("4617086"))
                .setAddress(new Address().setStreetName("Urquiza").setStreetNumber(1109).setZipCode("3100"));

        Preference preference = new Preference();
        return preference.setPayer(payer).appendItem(item).save();
    }
}
