package com.mercadolibre.router;

import com.google.common.net.MediaType;
import com.mercadolibre.util.JsonUtils;
import com.mercadopago.exceptions.MPException;
import org.apache.http.HttpStatus;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.servlet.SparkApplication;

import static com.mercadolibre.Constants.*;
import static spark.Spark.*;

public class RestEndpoint implements SparkApplication {

    @Override
    public void init() {
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        post(PATH_EJERCICIO_1,PreferenciaRoute::crearPreferenciaPost, JsonUtils::toJson);
        get(PATH_EJERCICIO_2, PreferenciaRoute::crearPreferencia);
        get(PATH_EJERCICIO_3, PagoRoute::getRecibirPagoView);
        post(PATH_EJERCICIO_3_PAGO, PagoRoute::recibirPago, JsonUtils::toJson);
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
