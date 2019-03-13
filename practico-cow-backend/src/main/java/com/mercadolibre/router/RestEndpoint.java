package com.mercadolibre.router;

import com.mercadolibre.util.JsonUtils;
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

        get(PATH_PREFERENCIA, PreferenciaRoute::crearPreferencia, JsonUtils::toJson);
        post(PATH_PAGO, PagoRoute::recibirPago, JsonUtils::toJson);
        post(PATH_PROCESAR_PAGO, PagoRoute::procesarPago, JsonUtils::toJson);
    }
}
