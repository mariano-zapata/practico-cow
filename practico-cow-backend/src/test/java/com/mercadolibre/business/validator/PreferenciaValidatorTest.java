package com.mercadolibre.business.validator;

import com.google.common.collect.Sets;
import com.mercadolibre.model.Pagador;
import com.mercadolibre.model.Preferencia;
import com.mercadolibre.model.Producto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.mercadolibre.Constants.ERROR_PREFERENCIA_PAGADOR;
import static com.mercadolibre.Constants.ERROR_PREFERENCIA_PRODUCTO;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PreferenciaValidatorTest {

    @Mock
    private PagadorValidator pagadorValidator;

    @Mock
    private ProductoValidator productoValidator;

    @InjectMocks
    private PreferenciaValidator validator;

    @Test
    public void testValidateExito() {
        Errors errors = mock(Errors.class);

        Pagador pagador = new Pagador();
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        Preferencia preferencia = new Preferencia();
        preferencia.setPagador(pagador).appendProducto(producto1).appendProducto(producto2);

        validator.validate(preferencia, errors);
        verifyZeroInteractions(errors);
        verify(pagadorValidator).validate(pagador, errors);
        verify(productoValidator).validate(producto1, errors);
        verify(productoValidator).validate(producto2, errors);
    }

    @Test
    public void testValidatePagador() {
        Errors errors = mock(Errors.class);

        Preferencia preferencia = new Preferencia();

        validator.validate(preferencia, errors);
        verify(errors).appendError(ERROR_PREFERENCIA_PAGADOR);
        verifyNoMoreInteractions(errors);
        verifyZeroInteractions(pagadorValidator, productoValidator);
    }

    @Test
    public void testValidateProductosNull() {
        Errors errors = mock(Errors.class);

        Preferencia preferencia = new Preferencia();
        preferencia.setPagador(new Pagador());

        validator.validate(preferencia, errors);
        verify(errors).appendError(ERROR_PREFERENCIA_PRODUCTO);
        verifyNoMoreInteractions(errors);
        verifyZeroInteractions(pagadorValidator, productoValidator);
    }

    @Test
    public void testValidateProductosEmpty() {
        Errors errors = mock(Errors.class);

        Preferencia preferencia = new Preferencia();
        preferencia.setPagador(new Pagador()).setProductos(Sets.newHashSet());

        validator.validate(preferencia, errors);
        verify(errors).appendError(ERROR_PREFERENCIA_PRODUCTO);
        verifyNoMoreInteractions(errors);
        verifyZeroInteractions(pagadorValidator, productoValidator);
    }
}
