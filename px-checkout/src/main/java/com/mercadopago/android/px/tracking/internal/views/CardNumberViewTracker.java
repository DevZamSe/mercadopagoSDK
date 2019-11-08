package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;

public class CardNumberViewTracker extends ViewTracker {

    private static final String CARD_NUMBER = "/number";

    public CardNumberViewTracker() {
    }

    @NonNull
    @Override
    public String getViewPath() {
        return BASE_VIEW_PATH + ADD_PAYMENT_METHOD_PATH + CARD_NUMBER;
    }
}
