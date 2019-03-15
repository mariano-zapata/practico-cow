package com.mercadolibre.router;

import com.google.common.net.MediaType;
import com.mercadolibre.util.JsonUtils;
import com.mercadopago.exceptions.MPException;
import org.apache.http.HttpStatus;
import spark.Filter;
import spark.Request;
import spark.Response;

import static com.mercadolibre.Constants.*;
import static spark.Spark.*;

public class RestEndpoint {

    public void init() {
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get(PATH_PREFERENCIA, PreferenciaRoute::crearPreferencia, JsonUtils::toJson);
        post(PATH_PREFERENCIA,PreferenciaRoute::crearPreferenciaPost, JsonUtils::toJson);
        post(PATH_PAGO, PagoRoute::recibirPago, JsonUtils::toJson);
        post(PATH_PROCESAR_PAGO, PagoRoute::procesarPago, JsonUtils::toJson);

        exception(MPException.class, RestEndpoint::mpExceptionHandler);
        exception(Exception.class, RestEndpoint::exceptionHandler);
    }

    private static void mpExceptionHandler(MPException e, Request request, Response response) {
        response.header(HEADER_CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        response.status(e.getStatusCode());
        RestEndpointResponse endpointResponse = new RestEndpointResponse(STATUS_ERROR, e.getMessage());
        response.body(JsonUtils.toJson(endpointResponse));
    }

    private static void exceptionHandler(Exception e, Request request, Response response) {
        response.header(HEADER_CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        RestEndpointResponse endpointResponse = new RestEndpointResponse(STATUS_ERROR, e.getMessage());
        response.body(JsonUtils.toJson(endpointResponse));
    }
}
