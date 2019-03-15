package com.mercadolibre.model;

import com.google.common.base.Objects;

public class Producto {

    private String id;

    private String titulo;

    private Integer cantidad;

    private String moneda;

    private Float precio;

    public String getId() {
        return id;
    }

    public Producto setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Producto setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Producto setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public String getMoneda() {
        return moneda;
    }

    public Producto setMoneda(String moneda) {
        this.moneda = moneda;
        return this;
    }

    public Float getPrecio() {
        return precio;
    }

    public Producto setPrecio(Float precio) {
        this.precio = precio;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equal(getId(), producto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
