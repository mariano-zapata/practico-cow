package com.mercadolibre.business.converter;

import com.mercadolibre.model.Pago;
import com.mercadopago.resources.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PaymentConverterTest {

    @InjectMocks
    private PaymentConverter converter;

    @Test
    public void testConvert() {
        Pago pago = new Pago();
        pago.setMonto(50.9F).setToken("token").setDescripcion("descripcion").setCuotas(3).setMetodoPagoId("visa")
                .setEmailPagador("pagador@mail.com");

        Payment payment = converter.convert(pago);
        assertEquals(pago.getMonto(), payment.getTransactionAmount());
        assertEquals(pago.getDescripcion(), payment.getDescription());
        assertEquals(pago.getCuotas(), payment.getInstallments());
        assertEquals(pago.getMetodoPagoId(), payment.getPaymentMethodId());
        assertNotNull(payment.getPayer());
        assertEquals(pago.getEmailPagador(), payment.getPayer().getEmail());
    }
}
