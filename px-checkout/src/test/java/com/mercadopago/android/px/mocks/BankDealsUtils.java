package com.mercadopago.android.px.mocks;

import com.google.gson.reflect.TypeToken;
import com.mercadopago.android.px.internal.util.JsonUtil;
import com.mercadopago.android.px.model.BankDeal;
import com.mercadopago.android.px.utils.ResourcesUtil;
import java.lang.reflect.Type;
import java.util.List;

public final class BankDealsUtils {

    private BankDealsUtils() {
    }

    public static List<BankDeal> getBankDealsListMLA() {
        // TODO it could be replaced for BankDealStubs?
        List<BankDeal> bankDealsList;
        final String json = ResourcesUtil.getStringResource("bank_deals.json");

        try {
            final Type listType = new TypeToken<List<BankDeal>>() {
            }.getType();
            bankDealsList = JsonUtil.fromJson(json, listType);
        } catch (final Exception ex) {
            bankDealsList = null;
        }
        return bankDealsList;
    }
}