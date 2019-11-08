package com.mercadopago.android.px.internal.repository;

import com.mercadopago.android.px.internal.callbacks.MPCall;
import com.mercadopago.android.px.model.PaymentMethod;
import java.util.List;

public interface PaymentMethodsRepository {

    /**
     * Get Payment Method's list for current merchant and user.
     *
     * @return Payment Method's list
     */
    MPCall<List<PaymentMethod>> getPaymentMethods();
}
