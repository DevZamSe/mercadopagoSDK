package com.mercadopago.android.px.tracking.internal.model;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.model.PaymentMethod;
import java.util.Map;

/**
 * Class used for Payment vault and Express checkout screen.
 */
@SuppressWarnings("unused")
@Keep
public class AvailableMethod extends TrackingMapModel {

    @Nullable
    /* default */ final String paymentMethodId;
    @NonNull
    /* default */ final String paymentMethodType;
    @Nullable
    /* default */ final Map<String, Object> extraInfo;

    public AvailableMethod(@NonNull final String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
        paymentMethodId = null;
        extraInfo = null;
    }

    public AvailableMethod(@NonNull final String paymentMethodId,
        @NonNull final String paymentMethodType,
        @NonNull final Map<String, Object> extraInfo) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodType = paymentMethodType;
        this.extraInfo = extraInfo;
    }

    public AvailableMethod(@Nullable final String paymentMethodId,
        @NonNull final String paymentMethodType) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodType = paymentMethodType;
        extraInfo = null;
    }

    public static AvailableMethod from(@NonNull final PaymentMethod paymentMethod) {
        return new AvailableMethod(paymentMethod.getId(), paymentMethod.getPaymentTypeId());
    }
}
