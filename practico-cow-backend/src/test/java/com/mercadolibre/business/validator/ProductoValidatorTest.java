package com.mercadolibre.business.validator;

import com.mercadolibre.model.Producto;
import com.mercadolibre.util.CurrencyEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.mercadolibre.Constants.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductoValidatorTest {

    @InjectMocks
    private ProductoValidator validator;

    @Test
    public void testValidateExito() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setId("1").setCantidad(5).setMoneda(CurrencyEnum.PESO_ARGENTINO.getId()).setPrecio(95.6F);

        validator.validate(producto, errors);
        verifyZeroInteractions(errors);
    }

    @Test
    public void testValidateId() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setCantidad(5).setMoneda(CurrencyEnum.PESO_ARGENTINO.getId()).setPrecio(95.6F);

        validator.validate(producto, errors);
        verify(errors).appendError(ERROR_PRODUCTO_ID);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateCantidadNull() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setId("1").setMoneda(CurrencyEnum.PESO_ARGENTINO.getId()).setPrecio(95.6F);

        validator.validate(producto, errors);
        verify(errors).appendError(ERROR_PRODUCTO_CANTIDAD);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateCantidadIncorrecta() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setId("1").setCantidad(0).setMoneda(CurrencyEnum.PESO_ARGENTINO.getId()).setPrecio(95.6F);

        validator.validate(producto, errors);
        verify(errors).appendError(ERROR_PRODUCTO_CANTIDAD);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateMonedaNull() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setId("1").setCantidad(6).setPrecio(95.6F);

        validator.validate(producto, errors);
        verify(errors).appendError(ERROR_PRODUCTO_MONEDA);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidateMonedaIncorrecta() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setId("1").setCantidad(6).setMoneda("USS").setPrecio(95.6F);

        validator.validate(producto, errors);
        verify(errors).appendError(ERROR_PRODUCTO_MONEDA);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidatePrecioNull() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setId("1").setCantidad(5).setMoneda(CurrencyEnum.PESO_ARGENTINO.getId());

        validator.validate(producto, errors);
        verify(errors).appendError(ERROR_PRODUCTO_PRECIO);
        verifyNoMoreInteractions(errors);
    }

    @Test
    public void testValidatePrecioIncorrecto() {
        Errors errors = mock(Errors.class);

        Producto producto = new Producto();
        producto.setId("1").setCantidad(5).setMoneda(CurrencyEnum.PESO_ARGENTINO.getId()).setPrecio(-0.1F);

        validator.validate(producto, errors);
        verify(errors).appendError(ERROR_PRODUCTO_PRECIO);
        verifyNoMoreInteractions(errors);
    }
}
