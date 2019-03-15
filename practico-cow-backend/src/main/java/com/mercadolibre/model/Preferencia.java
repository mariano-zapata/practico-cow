package com.mercadolibre.model;

import java.util.HashSet;
import java.util.Set;

public class Preferencia {

    private Pagador pagador;

    private Set<Producto> productos;

    public Pagador getPagador() {
        return pagador;
    }

    public Preferencia setPagador(Pagador pagador) {
        this.pagador = pagador;
        return this;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public Preferencia setProductos(Set<Producto> productos) {
        this.productos = productos;
        return this;
    }

    public Preferencia appendProducto(Producto producto) {
        if (productos == null) {
            productos = new HashSet<>();
        }
        productos.add(producto);
        return this;
    }
}
