package com.mercadolibre.business.converter;

import com.mercadolibre.model.Pagador;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class PayerConverterTest {

    @InjectMocks
    private PayerConverter converter;

    @Test
    public void testConvertDatosBasicos() {
        Pagador pagador = new Pagador().setApellido("Apellido").setNombre("Nombres").setEmail("correo@email.com");

        Payer payer = converter.convert(pagador);
        assertEquals(pagador.getApellido(), payer.getSurname());
        assertEquals(pagador.getNombre(), payer.getName());
        assertEquals(pagador.getEmail(), payer.getEmail());
        assertNull(payer.getPhone());
        assertNull(payer.getAddress());
    }

    @Test
    public void testConvertPhone() {
        Pagador pagador = new Pagador().setApellido("Apellido").setNombre("Nombres").setEmail("correo@email.com")
                .setTelefonoCodigoArea("0343").setTelefonoNumero("154617086");

        Payer payer = converter.convert(pagador);
        assertEquals(pagador.getApellido(), payer.getSurname());
        assertEquals(pagador.getNombre(), payer.getName());
        assertEquals(pagador.getEmail(), payer.getEmail());
        assertNotNull(payer.getPhone());
        assertEquals(pagador.getTelefonoCodigoArea(), payer.getPhone().getAreaCode());
        assertEquals(pagador.getTelefonoNumero(), payer.getPhone().getNumber());
        assertNull(payer.getAddress());
    }

    @Test
    public void testConvertPhoneAndAddress() {
        Pagador pagador = new Pagador().setApellido("Apellido").setNombre("Nombres").setEmail("correo@email.com")
                .setTelefonoCodigoArea("0343").setTelefonoNumero("154617086")
                .setDireccionCalle("Corrientes").setDireccionNumero(779).setDireccionCodigoPostal("3100");

        Payer payer = converter.convert(pagador);
        assertEquals(pagador.getApellido(), payer.getSurname());
        assertEquals(pagador.getNombre(), payer.getName());
        assertEquals(pagador.getEmail(), payer.getEmail());
        assertNotNull(payer.getPhone());
        assertEquals(pagador.getTelefonoCodigoArea(), payer.getPhone().getAreaCode());
        assertEquals(pagador.getTelefonoNumero(), payer.getPhone().getNumber());
        assertNotNull(payer.getAddress());
        assertEquals(pagador.getDireccionCalle(), payer.getAddress().getStreetName());
        assertEquals(pagador.getDireccionNumero(), payer.getAddress().getStreetNumber());
        assertEquals(pagador.getDireccionCodigoPostal(), payer.getAddress().getZipCode());
    }
}
