package com.mercadolibre.business.converter;

import com.mercadolibre.model.Preferencia;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;

public class PreferenceConverter implements Converter<Preferencia, Preference> {

    private PayerConverter payerConverter;

    private ItemConverter itemConverter;

    public PreferenceConverter() {
        payerConverter = new PayerConverter();
        itemConverter = new ItemConverter();
    }

    @Override
    public Preference convert(Preferencia source) {
        Preference preference = new Preference();

        Payer payer = payerConverter.convert(source.getPagador());
        preference.setPayer(payer);

        source.getProductos().forEach(producto -> preference.appendItem(itemConverter.convert(producto)));

        return preference;
    }
}
