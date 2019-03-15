package com.mercadolibre.util;

import com.google.gson.JsonSyntaxException;
import com.mercadopago.exceptions.MPException;
import org.apache.http.HttpStatus;
import spark.Request;

public class RequestHandler {

    public static <R> R handle(Request request, Class<R> resultType) throws MPException {
        try {
            if (request.body().isEmpty()) {
                throw new MPException("Empty json body", null, HttpStatus.SC_BAD_REQUEST);
            }
            return JsonUtils.fromJson(request.body(), resultType);
        } catch (JsonSyntaxException e) {
            throw new MPException(e.getMessage(), null, HttpStatus.SC_BAD_REQUEST);
        }
    }
}
