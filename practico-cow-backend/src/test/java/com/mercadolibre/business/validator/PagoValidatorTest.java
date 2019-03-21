package com.mercadolibre.business.validator;

import com.mercadolibre.model.Pago;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.mercadolibre.Constants.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PagoValidatorTest {

    @InjectMocks
    private PagoValidator validator;

    @Test
    public void testValidateExito() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setMonto(500F).setToken("token").setCuotas(3).setMetodoPagoId("visa").setEmailPagador("pagador@mail.com");

        validator.validate(pago, errors);
        verifyZeroInteractions(errors);
    }

    @Test
    public void testValidateMontoNull() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setToken("token").setCuotas(3).setMetodoPagoId("visa").setEmailPagador("pagador@mail.com");

        validator.validate(pago, errors);
        verify(errors).appendError(ERROR_PAGO_MONTO);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateMontoIncorrecto() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setMonto(0F).setToken("token").setCuotas(3).setMetodoPagoId("visa").setEmailPagador("pagador@mail.com");

        validator.validate(pago, errors);
        verify(errors).appendError(ERROR_PAGO_MONTO);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateToken() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setMonto(60F).setCuotas(3).setMetodoPagoId("visa").setEmailPagador("pagador@mail.com");

        validator.validate(pago, errors);
        verify(errors).appendError(ERROR_PAGO_TOKEN);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateCuotasNull() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setMonto(60F).setToken("token").setMetodoPagoId("visa").setEmailPagador("pagador@mail.com");

        validator.validate(pago, errors);
        verify(errors).appendError(ERROR_PAGO_CUOTAS);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateCuotasIncorrectas() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setMonto(60F).setToken("token").setCuotas(0).setMetodoPagoId("visa").setEmailPagador("pagador@mail.com");

        validator.validate(pago, errors);
        verify(errors).appendError(ERROR_PAGO_CUOTAS);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateMetodoPagoId() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setMonto(60F).setToken("token").setCuotas(3).setMetodoPagoId("").setEmailPagador("pagador@mail.com");

        validator.validate(pago, errors);
        verify(errors).appendError(ERROR_PAGO_METODO);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateEmailPagador() {
        Errors errors = mock(Errors.class);

        Pago pago = new Pago();
        pago.setMonto(60F).setToken("token").setCuotas(3).setMetodoPagoId("visa");

        validator.validate(pago, errors);
        verify(errors).appendError(ERROR_PAGO_EMAIL);
        verifyNoMoreInteractions(errors);
    }
}
