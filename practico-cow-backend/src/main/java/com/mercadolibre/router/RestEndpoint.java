package com.mercadolibre.router;

import com.mercadolibre.Utils;
import spark.Filter;

import static com.mercadolibre.Constants.*;
import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

public class RestEndpoint {

    public void init() {
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get(PATH_PREFERENCIA, PreferenciaRoute::crearPreferencia, Utils.json());
        post(PATH_PAGO, PagoRoute::recibirPago, Utils.json());
        post(PATH_PROCESAR_PAGO, PagoRoute::procesarPago, Utils.json());
    }
}
