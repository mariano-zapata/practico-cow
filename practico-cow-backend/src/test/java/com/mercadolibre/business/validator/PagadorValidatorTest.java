package com.mercadolibre.business.validator;

import com.mercadolibre.model.Pagador;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.mercadolibre.Constants.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PagadorValidatorTest {

    @InjectMocks
    private PagadorValidator validator;

    @Test
    public void testValidateExito() {
        Errors errors = mock(Errors.class);

        Pagador pagador = new Pagador();
        pagador.setApellido("Apellido").setNombre("Nombre").setEmail("correo@mail.com");

        validator.validate(pagador, errors);
        verifyZeroInteractions(errors);
    }

    @Test
    public void testValidateApellido() {
        Errors errors = mock(Errors.class);

        Pagador pagador = new Pagador();
        pagador.setNombre("Nombre").setEmail("correo@mail.com");

        validator.validate(pagador, errors);
        verify(errors).appendError(ERROR_PAGADOR_APELLIDO);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateNombre() {
        Errors errors = mock(Errors.class);

        Pagador pagador = new Pagador();
        pagador.setApellido("Apellido").setEmail("correo@mail.com");

        validator.validate(pagador, errors);
        verify(errors).appendError(ERROR_PAGADOR_NOMBRE);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateEmail() {
        Errors errors = mock(Errors.class);

        Pagador pagador = new Pagador();
        pagador.setApellido("Apellido").setNombre("Nombre");

        validator.validate(pagador, errors);
        verify(errors).appendError(ERROR_PAGADOR_EMAIL);
        verifyNoMoreInteractions(errors);
    }
}
