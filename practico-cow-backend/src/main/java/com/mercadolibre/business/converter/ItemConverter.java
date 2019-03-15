package com.mercadolibre.business.converter;

import com.mercadolibre.model.Producto;
import com.mercadopago.resources.datastructures.preference.Item;

public class ItemConverter implements Converter<Producto, Item> {

    @Override
    public Item convert(Producto source) {
        Item item = new Item();
        item.setId(source.getId()).setTitle(source.getTitulo()).setQuantity(source.getCantidad())
                .setCurrencyId(source.getMoneda()).setUnitPrice(source.getPrecio());
        return item;
    }
}
