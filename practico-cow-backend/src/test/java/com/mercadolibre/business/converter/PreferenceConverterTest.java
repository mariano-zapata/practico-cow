package com.mercadolibre.business.converter;

import com.google.common.collect.Sets;
import com.mercadolibre.model.Pagador;
import com.mercadolibre.model.Preferencia;
import com.mercadolibre.model.Producto;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PreferenceConverterTest {

    @Mock
    private PayerConverter payerConverter;

    @Mock
    private ItemConverter itemConverter;

    @InjectMocks
    private PreferenceConverter converter;

    @Test
    public void testConvertSinItems() {
        Pagador pagador = new Pagador();
        Preferencia preferencia = new Preferencia();
        preferencia.setPagador(pagador);
        preferencia.setProductos(Sets.newHashSet());

        Payer payer = mock(Payer.class);
        when(payerConverter.convert(pagador)).thenReturn(payer);

        Preference result = converter.convert(preferencia);
        verify(payerConverter).convert(pagador);
        assertEquals(payer, result.getPayer());
        verifyZeroInteractions(itemConverter);
    }

    @Test
    public void testConvert() {
        Pagador pagador = new Pagador();
        Producto producto = new Producto("1");
        Preferencia preferencia = new Preferencia();
        preferencia.setPagador(pagador);
        preferencia.appendProducto(producto);

        Payer payer = mock(Payer.class);
        when(payerConverter.convert(pagador)).thenReturn(payer);

        Item item = mock(Item.class);
        when(itemConverter.convert(producto)).thenReturn(item);

        Preference result = converter.convert(preferencia);
        verify(payerConverter).convert(pagador);
        assertEquals(payer, result.getPayer());
        verify(itemConverter).convert(producto);
        assertEquals(1, result.getItems().size());
        assertEquals(item, result.getItems().get(0));
    }
}
