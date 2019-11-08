package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;

public class BankDealsDetailViewTracker extends ViewTracker {

    private static final String PATH = BASE_VIEW_PATH + ADD_PAYMENT_METHOD_PATH + "/promotions/terms_and_conditions";

    @NonNull
    @Override
    public String getViewPath() {
        return PATH;
    }
}
