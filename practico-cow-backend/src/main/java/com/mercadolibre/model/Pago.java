package com.mercadolibre.model;

public class Pago {

    private Float monto;

    private String token;

    private String descripcion;

    private Integer cuotas;

    private String metodoPagoId;

    private String emailPagador;

    public Float getMonto() {
        return monto;
    }

    public Pago setMonto(Float monto) {
        this.monto = monto;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Pago setToken(String token) {
        this.token = token;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Pago setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public Pago setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
        return this;
    }

    public String getMetodoPagoId() {
        return metodoPagoId;
    }

    public Pago setMetodoPagoId(String metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
        return this;
    }

    public String getEmailPagador() {
        return emailPagador;
    }

    public Pago setEmailPagador(String emailPagador) {
        this.emailPagador = emailPagador;
        return this;
    }
}
