package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;
import com.mercadopago.android.px.tracking.internal.model.ApiErrorData;
import java.util.Map;

public class ErrorViewTracker extends ViewTracker {

    private static final String PATH = BASE_VIEW_PATH + "/generic_error";
    private final String errorMessage;
    @NonNull private final MercadoPagoError mpError;

    public ErrorViewTracker(final String errorMessage,
        @NonNull final MercadoPagoError mpError) {
        this.errorMessage = errorMessage;
        this.mpError = mpError;
    }

    @NonNull
    @Override
    public Map<String, Object> getData() {
        final Map<String, Object> data = super.getData();
        data.put("error_message", errorMessage);
        data.put("api_error", new ApiErrorData(mpError).toMap());
        return data;
    }

    @NonNull
    @Override
    public String getViewPath() {
        return PATH;
    }
}
