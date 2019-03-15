package com.mercadolibre.business.converter;

import com.mercadolibre.model.Pagador;
import com.mercadopago.resources.datastructures.preference.Address;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Phone;
import org.apache.commons.lang3.StringUtils;

public class PayerConverter implements Converter<Pagador, Payer> {

    @Override
    public Payer convert(Pagador source) {
        Payer payer = new Payer();
        payer.setName(source.getNombre()).setSurname(source.getApellido()).setEmail(source.getEmail());
        if (StringUtils.isNotEmpty(source.getTelefonoCodigoArea())
                && StringUtils.isNotEmpty(source.getTelefonoNumero())) {
            payer.setPhone(new Phone().setAreaCode(source.getTelefonoCodigoArea())
                    .setNumber(source.getTelefonoNumero()));
        }
        if (StringUtils.isNotEmpty(source.getDireccionCalle()) && source.getDireccionNumero() != null
                && StringUtils.isNotEmpty(source.getDireccionCodigoPostal())) {
            payer.setAddress(new Address().setStreetName(source.getDireccionCalle())
                    .setStreetNumber(source.getDireccionNumero()).setZipCode(source.getDireccionCodigoPostal()));
        }
        return payer;
    }
}
