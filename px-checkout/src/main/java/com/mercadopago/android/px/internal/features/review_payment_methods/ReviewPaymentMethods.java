package com.mercadopago.android.px.internal.features.review_payment_methods;

import com.mercadopago.android.px.internal.base.MvpView;
import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;
import java.util.List;

/* default */ interface ReviewPaymentMethods {

    /* default */ interface View extends MvpView {
        void showError(final MercadoPagoError error, final String requestOrigin);

        void initializeSupportedPaymentMethods(final List<PaymentMethod> supportedPaymentMethods);
    }

    /* default */ interface Actions {
        void initialize();
    }
}
