package com.mercadolibre.business.converter;

import com.mercadolibre.model.Producto;
import com.mercadolibre.util.CurrencyEnum;
import com.mercadopago.resources.datastructures.preference.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemConverterTest {

    @InjectMocks
    private ItemConverter converter;

    @Test
    public void testConvert() {
        Producto producto = new Producto("1").setCantidad(5).setMoneda(CurrencyEnum.PESO_ARGENTINO.getId())
                .setPrecio(950F).setTitulo("Producto 1");

        Item item = converter.convert(producto);
        assertEquals(producto.getId(), item.getId());
        assertEquals(producto.getCantidad(), item.getQuantity());
        assertEquals(producto.getMoneda(), item.getCurrencyId());
        assertEquals(producto.getTitulo(), item.getTitle());
        assertEquals(producto.getPrecio(), item.getUnitPrice());
    }
}
