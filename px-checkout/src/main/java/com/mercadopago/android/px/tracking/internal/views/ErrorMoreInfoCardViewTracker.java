package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;

public class ErrorMoreInfoCardViewTracker extends ViewTracker {

    private static final String PATH_EXCLUDED_CARD = BASE_VIEW_PATH + ADD_PAYMENT_METHOD_PATH + "/number/error_more_info";

    @NonNull
    @Override
    public String getViewPath() {
        return PATH_EXCLUDED_CARD;
    }
}
