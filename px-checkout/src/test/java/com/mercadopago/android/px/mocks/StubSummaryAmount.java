package com.mercadopago.android.px.mocks;

import com.mercadopago.android.px.internal.util.JsonUtil;
import com.mercadopago.android.px.model.SummaryAmount;
import com.mercadopago.android.px.utils.ResourcesUtil;

public final class StubSummaryAmount {

    private StubSummaryAmount() {
    }

    public static SummaryAmount getSummaryAmountTwoPayerCosts() {
        return getFromFile("summary_amount_two_payer_costs.json");
    }

    private static SummaryAmount getFromFile(final String fileName) {
        return JsonUtil
            .fromJson(ResourcesUtil.getStringResource(fileName),
                SummaryAmount.class);
    }
}