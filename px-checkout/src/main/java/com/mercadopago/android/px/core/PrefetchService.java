package com.mercadopago.android.px.core;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.di.Session;
import com.mercadopago.android.px.internal.util.ApiUtil;
import com.mercadopago.android.px.model.exceptions.ApiException;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;
import com.mercadopago.android.px.model.internal.InitResponse;
import com.mercadopago.android.px.services.Callback;
import com.mercadopago.android.px.tracking.internal.events.FrictionEventTracker;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mercadopago.android.px.tracking.internal.events.FrictionEventTracker.Id.GENERIC;
import static com.mercadopago.android.px.tracking.internal.events.FrictionEventTracker.Style.NON_SCREEN;

/* default */ class PrefetchService {

    private static final ExecutorService EXECUTOR_QUEUE = Executors.newSingleThreadExecutor();

    private final Handler mainHandler;

    /* default */ @NonNull final Session session;
    /* default */ @NonNull CheckoutLazyInit internalCallback;
    /* default */ @NonNull final MercadoPagoCheckout checkout;

    /* default */ PrefetchService(@NonNull final MercadoPagoCheckout checkout, @NonNull final Session session,
        @NonNull final CheckoutLazyInit internalCallback) {
        this.checkout = checkout;
        this.session = session;
        this.internalCallback = internalCallback;
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void prefetch() {
        EXECUTOR_QUEUE.execute(() -> {
            session.init(checkout);
            initCall();
        });
    }

    /* default */ void initCall() {
        session.getInitRepository().init().execute(new Callback<InitResponse>() {

            @Override
            public void success(final InitResponse initResponse) {
                postSuccess();
            }

            @Override
            public void failure(final ApiException apiException) {
                postError(apiException);
            }
        });
    }

    /* default */ void postSuccess() {
        mainHandler.post(() -> {
            checkout.prefetch = true;
            internalCallback.success(checkout);
        });
    }

    /* default */ void postError(final ApiException apiException) {
        mainHandler.post(() -> {
            FrictionEventTracker.with("/px_checkout/lazy_init", GENERIC, NON_SCREEN,
                new MercadoPagoError(apiException, ApiUtil.RequestOrigin.POST_INIT));
            internalCallback.failure();
        });
    }

    public void cancel() {
        internalCallback = new CheckoutLazyInit(new MercadoPagoCheckout.Builder("", "")) {
            @Override
            public void fail(@NonNull final MercadoPagoCheckout mercadoPagoCheckout) {
                // do nothing
            }

            @Override
            public void success(@NonNull final MercadoPagoCheckout mercadoPagoCheckout) {
                // do nothing
            }
        };
    }
}