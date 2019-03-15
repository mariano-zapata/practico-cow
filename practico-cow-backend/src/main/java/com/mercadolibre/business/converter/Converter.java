package com.mercadolibre.business.converter;

public interface Converter<T, R> {

    R convert(T source);
}
