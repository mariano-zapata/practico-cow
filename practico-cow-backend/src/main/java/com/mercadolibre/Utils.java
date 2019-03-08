package com.mercadolibre;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class Utils {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return Utils::toJson;
    }
}
