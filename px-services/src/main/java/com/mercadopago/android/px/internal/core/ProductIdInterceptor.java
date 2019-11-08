package com.mercadopago.android.px.internal.core;

import android.content.Context;
import android.support.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class ProductIdInterceptor implements Interceptor {

    private static final String HEADER_KEY = "X-Product-Id";

    @NonNull private final Context context;

    public ProductIdInterceptor(@NonNull final Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        final Request originalRequest = chain.request();
        final Request request = originalRequest.newBuilder()
            .header(HEADER_KEY, new ApplicationModule(context).getProductIdProvider().getProductId())
            .build();
        return chain.proceed(request);
    }
}