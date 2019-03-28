package com.mercadolibre;

import com.mercadolibre.router.RestEndpoint;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import spark.Spark;

import static com.mercadolibre.Constants.ACCESS_TOKEN;
import static com.mercadolibre.Constants.CLIENT_ID;
import static com.mercadolibre.Constants.CLIENT_SECRET;

public class Main {

    public static void main(final String[] args) throws MPException {
        Spark.port(8080);

        MercadoPago.SDK.setClientId(CLIENT_ID);
        MercadoPago.SDK.setClientSecret(CLIENT_SECRET);
        MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);

        new RestEndpoint().init();
    }
}
