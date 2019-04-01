package com.mercadolibre.business.service;

import com.mercadolibre.business.converter.PreferenceConverter;
import com.mercadolibre.business.validator.Errors;
import com.mercadolibre.business.validator.PreferenciaValidator;
import com.mercadolibre.model.Preferencia;
import com.mercadolibre.util.PracticoException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PreferenciaServiceTest {

    @Mock
    private PreferenciaValidator preferenciaValidator;

    @Mock
    private PreferenceConverter preferenceConverter;

    @InjectMocks
    private PreferenciaService service = PreferenciaService.getInstance();

    @Test
    public void testSaveExito() throws MPException {
        Preferencia preferencia = mock(Preferencia.class);
        Preference preference = mock(Preference.class);

        when(preferenceConverter.convert(preferencia)).thenReturn(preference);

        service.save(preferencia);
        verify(preferenciaValidator).validate(eq(preferencia), any(Errors.class));
        verify(preferenceConverter).convert(preferencia);
        verify(preference).save();
    }

    @Test(expected = PracticoException.class)
    public void testSaveValidationError() throws MPException {
        Preferencia preferencia = mock(Preferencia.class);

        doAnswer(invocation -> {
            Errors errors = (Errors) invocation.getArguments()[1];
            errors.appendError("Un error de validacion");
            return null;
        }).when(preferenciaValidator).validate(any(Preferencia.class), any(Errors.class));

        service.save(preferencia);
        verify(preferenciaValidator).validate(eq(preferencia), any(Errors.class));
        verifyZeroInteractions(preferenceConverter);
    }

    @Test(expected = MPException.class)
    public void testSavePreferenceError() throws MPException {
        Preferencia preferencia = mock(Preferencia.class);
        Preference preference = mock(Preference.class);

        when(preferenceConverter.convert(preferencia)).thenReturn(preference);
        when(preference.save()).thenThrow(MPException.class);

        service.save(preferencia);
        verify(preferenciaValidator).validate(eq(preferencia), any(Errors.class));
        verify(preferenceConverter).convert(preferencia);
        verify(preference).save();
    }
}
