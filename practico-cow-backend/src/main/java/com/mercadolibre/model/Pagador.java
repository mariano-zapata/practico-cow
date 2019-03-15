package com.mercadolibre.model;

public class Pagador {

    private String nombre;

    private String apellido;

    private String email;

    private String telefonoCodigoArea;

    private String telefonoNumero;

    private String direccionCalle;

    private Integer direccionNumero;

    private String direccionCodigoPostal;

    public String getNombre() {
        return nombre;
    }

    public Pagador setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public Pagador setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Pagador setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefonoCodigoArea() {
        return telefonoCodigoArea;
    }

    public Pagador setTelefonoCodigoArea(String telefonoCodigoArea) {
        this.telefonoCodigoArea = telefonoCodigoArea;
        return this;
    }

    public String getTelefonoNumero() {
        return telefonoNumero;
    }

    public Pagador setTelefonoNumero(String telefonoNumero) {
        this.telefonoNumero = telefonoNumero;
        return this;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public Pagador setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
        return this;
    }

    public Integer getDireccionNumero() {
        return direccionNumero;
    }

    public Pagador setDireccionNumero(Integer direccionNumero) {
        this.direccionNumero = direccionNumero;
        return this;
    }

    public String getDireccionCodigoPostal() {
        return direccionCodigoPostal;
    }

    public Pagador setDireccionCodigoPostal(String direccionCodigoPostal) {
        this.direccionCodigoPostal = direccionCodigoPostal;
        return this;
    }
}
