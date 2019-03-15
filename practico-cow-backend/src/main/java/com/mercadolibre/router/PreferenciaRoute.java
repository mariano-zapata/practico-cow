package com.mercadolibre.router;

import com.google.common.net.MediaType;
import com.mercadolibre.business.service.PreferenciaService;
import com.mercadolibre.model.Preferencia;
import com.mercadolibre.util.RequestHandler;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Address;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Phone;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import static com.mercadolibre.Constants.*;

public class PreferenciaRoute {

    public static Object crearPreferencia(Request request, Response response) throws MPException {
        MercadoPago.SDK.setClientId(CLIENT_ID);
        MercadoPago.SDK.setClientSecret(CLIENT_SECRET);

        Item item = new Item();
        item.setId("1234").setTitle("Product").setQuantity(1).setCurrencyId("ARS").setUnitPrice(95.7F);

        Payer payer = new Payer();
        payer.setName("Carlos").setSurname("Ocasio").setEmail("test_user_58877546@testuser.com")
                .setDateCreated("2018-06-02T12:58:41.425-04:00")
                .setPhone(new Phone().setAreaCode("343").setNumber("4617086"))
                .setAddress(new Address().setStreetName("Urquiza").setStreetNumber(1109).setZipCode("3100"));

        Preference preference = new Preference();
        return preference.setPayer(payer).appendItem(item).save();
    }

    public static Object crearPreferenciaPost(Request request, Response response) throws MPException {
        Preferencia preferencia = RequestHandler.handle(request, Preferencia.class);

        PreferenciaService preferenciaService = new PreferenciaService();
        Preference preference = preferenciaService.save(preferencia);

        response.header(HEADER_CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        response.status(HttpStatus.SC_OK);
        String responseMessage = String.format(PREFERENCIA_GENERADA, preference.getInitPoint());
        RestEndpointResponse endpointResponse = new RestEndpointResponse(STATUS_OK, responseMessage);
        return endpointResponse;
    }
}
