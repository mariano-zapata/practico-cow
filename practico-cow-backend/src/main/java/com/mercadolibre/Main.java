package com.mercadolibre;

import com.mercadolibre.router.RestEndpoint;
import spark.Spark;

public class Main {

    public static void main(final String[] args) {
        Spark.port(8080);

        new RestEndpoint().init();
    }
}
