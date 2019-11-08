package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;
import java.util.Map;

public class CardHolderNameViewTracker extends ViewTracker {

    public static final String CARD_HOLDER_NAME = "/name";

    @NonNull private final String paymentMethodTypeId;
    @NonNull private final String paymentMethodId;

    public CardHolderNameViewTracker(@NonNull final String paymentMethodTypeId, @NonNull final String paymentMethodId) {
        this.paymentMethodTypeId = paymentMethodTypeId;
        this.paymentMethodId = paymentMethodId;
    }

    @NonNull
    @Override
    public Map<String, Object> getData() {
        final Map<String, Object> data = super.getData();
        data.put("payment_method_id", paymentMethodId);
        return data;
    }

    @NonNull
    @Override
    public String getViewPath() {
        return BASE_VIEW_PATH + ADD_PAYMENT_METHOD_PATH + "/" + paymentMethodTypeId + CARD_HOLDER_NAME;
    }
}
