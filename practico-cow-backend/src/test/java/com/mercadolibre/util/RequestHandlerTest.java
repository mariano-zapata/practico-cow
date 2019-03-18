package com.mercadolibre.util;

import com.mercadolibre.model.Preferencia;
import com.mercadolibre.model.Producto;
import com.mercadopago.exceptions.MPException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import spark.Request;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RequestHandlerTest {

    @Test(expected = MPException.class)
    public void testHandleEmptyBody() throws MPException {
        Request request = mock(Request.class);
        when(request.body()).thenReturn("");

        RequestHandler.handle(request, Preferencia.class);
    }

    @Test(expected = MPException.class)
    public void testHandleBadBody() throws MPException {
        Request request = mock(Request.class);
        when(request.body()).thenReturn("Body con formato json incorrecto");

        RequestHandler.handle(request, Preferencia.class);
    }

    @Test
    public void testHandleSinItems() throws MPException {
        String json = "{pagador: {nombre: Pepe, apellido: Gomez, email: pgomez@mail.com}}";
        Request request = mock(Request.class);
        when(request.body()).thenReturn(json);

        Preferencia preferencia = RequestHandler.handle(request, Preferencia.class);
        assertNotNull(preferencia);
        assertNotNull(preferencia.getPagador());
        assertNull(preferencia.getProductos());
        assertEquals("Pepe", preferencia.getPagador().getNombre());
        assertEquals("Gomez", preferencia.getPagador().getApellido());
        assertEquals("pgomez@mail.com", preferencia.getPagador().getEmail());
    }

    @Test
    public void testHandleConItems() throws MPException {
        String pagador = "pagador: {nombre: Pepe, apellido: Gomez, email: pgomez@mail.com}, ";
        String item1 = "{id: 1, titulo: Producto1, cantidad: 5, moneda: ARS, precio: 750.35}, ";
        String item2 = "{id: 2, titulo: Producto2, cantidad: 9, moneda: ARS, precio: 900}";
        StringBuilder json = new StringBuilder("{");
        json.append(pagador).append("productos: [").append(item1).append(item2).append("]}");
        Request request = mock(Request.class);
        when(request.body()).thenReturn(json.toString());

        Preferencia preferencia = RequestHandler.handle(request, Preferencia.class);
        assertNotNull(preferencia);
        assertNotNull(preferencia.getPagador());
        assertNotNull(preferencia.getProductos());
        assertEquals(2, preferencia.getProductos().size());
        assertEquals("Pepe", preferencia.getPagador().getNombre());
        assertEquals("Gomez", preferencia.getPagador().getApellido());
        assertEquals("pgomez@mail.com", preferencia.getPagador().getEmail());
        assertTrue(preferencia.getProductos().contains(new Producto("1")));
        assertTrue(preferencia.getProductos().contains(new Producto("2")));
    }
}
