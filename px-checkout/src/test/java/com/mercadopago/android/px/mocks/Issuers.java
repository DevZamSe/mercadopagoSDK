package com.mercadopago.android.px.mocks;

import com.google.gson.reflect.TypeToken;
import com.mercadopago.android.px.internal.util.JsonUtil;
import com.mercadopago.android.px.model.Issuer;
import com.mercadopago.android.px.utils.ResourcesUtil;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public final class Issuers {
    private Issuers() {
        //TODO it could be replaced for IssuerStubs?
    }

    public static List<Issuer> getOneIssuerListMLA() {
        return Collections.singletonList(getIssuersListMLA().get(0));
    }

    public static List<Issuer> getIssuersListMLA() {
        List<Issuer> issuerList;
        final String json = ResourcesUtil.getStringResource("issuers_MLA.json");

        try {
            final Type listType = new TypeToken<List<Issuer>>() {
            }.getType();
            issuerList = JsonUtil.fromJson(json, listType);
        } catch (final Exception ex) {
            issuerList = null;
        }
        return issuerList;
    }
}