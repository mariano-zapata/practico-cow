package com.mercadolibre.business.service;

import com.mercadolibre.business.converter.PaymentConverter;
import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.business.validator.PagoValidator;
import com.mercadolibre.model.Pago;
import com.mercadolibre.util.PracticoException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PagoServiceTest {

    @Mock
    private PagoValidator pagoValidator;

    @Mock
    private PaymentConverter paymentConverter;

    @InjectMocks
    private PagoService service = PagoService.getInstance();

    @Test
    public void testSaveExito() throws MPException {
        Pago pago = mock(Pago.class);
        Payment payment = mock(Payment.class);

        when(paymentConverter.convert(pago)).thenReturn(payment);

        service.save(pago);
        verify(pagoValidator).validate(eq(pago), any(Errors.class));
        verify(paymentConverter).convert(pago);
        verify(payment).save();
    }

    @Test(expected = PracticoException.class)
    public void testSaveValidationError() throws MPException {
        Pago pago = mock(Pago.class);

        doAnswer(invocation -> {
            Errors errors = (Errors) invocation.getArguments()[1];
            errors.appendError("Un error de validacion");
            return null;
        }).when(pagoValidator).validate(any(Pago.class), any(Errors.class));

        service.save(pago);
        verify(pagoValidator).validate(eq(pago), any(Errors.class));
        verifyZeroInteractions(paymentConverter);
    }

    @Test(expected = MPException.class)
    public void testSavePaymentError() throws MPException {
        Pago pago = mock(Pago.class);
        Payment payment = mock(Payment.class);

        when(paymentConverter.convert(pago)).thenReturn(payment);
        when(payment.save()).thenThrow(MPException.class);

        service.save(pago);
        verify(pagoValidator).validate(eq(pago), any(Errors.class));
        verify(paymentConverter).convert(pago);
        verify(payment).save();
    }
}
