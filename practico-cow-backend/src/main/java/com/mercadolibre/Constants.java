package com.mercadolibre;

public class Constants {

    // Paths
    public static String PATH_TP = "/practico";

    public static String PATH_EJERCICIO_1 = PATH_TP + "/ejercicio1";

    public static String PATH_EJERCICIO_2 = PATH_TP + "/ejercicio2";

    public static String PATH_EJERCICIO_3 = PATH_TP + "/ejercicio3";

    public static String PATH_EJERCICIO_3_PAGO = PATH_TP + "/pago";

    public static String PATH_EJERCICIO_4 = PATH_TP + "/ejercicio4";

    public static String PATH_EJERCICIO_4_PROCESAR_PAGO = PATH_TP + "/procesar-pago";

    // Claves
    public static String CLIENT_ID = "3171675646578969";

    public static String CLIENT_SECRET = "ju7qd9Ec2XR3aGNcxMFbV0JMzsxK1s3p";

    public static String ACCESS_TOKEN = "TEST-3171675646578969-022708-663fcd89e9e29cf79f1a87632c13284e-403574962";

    // Crear preferencia
    public static String ERROR_PRODUCTO_ID = "Debe indicar el ID de producto";

    public static String ERROR_PRODUCTO_CANTIDAD = "La cantidad del producto es incorrecta";

    public static String ERROR_PRODUCTO_MONEDA = "La moneda indicada en el producto es incorrecta";

    public static String ERROR_PRODUCTO_PRECIO = "Debe indicar el precio del producto";

    public static String ERROR_PAGADOR_APELLIDO = "Debe indicar el apellido del pagador";

    public static String ERROR_PAGADOR_NOMBRE = "Debe indicar el nombre del pagador";

    public static String ERROR_PAGADOR_EMAIL = "Debe indicar el email del pagador";

    public static String ERROR_PREFERENCIA_PRODUCTO = "Debe indicar los datos del producto para poder crear la preferencia";

    public static String ERROR_PREFERENCIA_PAGADOR = "Debe indicar los datos del comprador para poder crear la preferencia";

    public static String PREFERENCIA_GENERADA = "Preferencia generada. Initpoint: %s";

    // Pago V1
    public static String ERROR_PAGO_MONTO = "El monto del pago es incorrecto";

    public static String ERROR_PAGO_TOKEN = "No se ha recibido el token del pago";

    public static String ERROR_PAGO_CUOTAS = "La cantidad de cuotas es incorrecta";

    public static String ERROR_PAGO_METODO = "Se debe indicar el método de pago a utilizar";

    public static String ERROR_PAGO_EMAIL = "Se debe informar el email del pagador";

    public static String PAGO_PROCESADO = "Pago procesado. Estado: %s - Detalle: %s";

    // Status
    public static String STATUS_OK = "200 - OK";

    public static String STATUS_ERROR = "ERROR";

    // Headers
    public static String HEADER_CONTENT_TYPE = "Content-Type";

    // Templates
    public static String TEMPLATE_EJERCICIO_2 = "/velocity/ejercicio2.vm";

    public static String TEMPLATE_EJERCICIO_3 = "/velocity/ejercicio3.vm";

    public static String TEMPLATE_EJERCICIO_4 = "/velocity/ejercicio4.vm";

    private Constants() {}
}
