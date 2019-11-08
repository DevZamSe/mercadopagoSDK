package com.mercadopago.android.px.testcheckout.assertions;

import android.support.annotation.NonNull;

import com.mercadopago.android.px.core.SplitPaymentProcessor;

public interface ProcessorValidator {
    void validate(@NonNull final SplitPaymentProcessor.CheckoutData checkoutData);
}