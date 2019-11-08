package com.mercadopago.android.px.internal.features.payment_methods;

import com.mercadopago.android.px.internal.base.MvpView;
import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;
import com.mercadopago.android.px.preferences.PaymentPreference;
import java.util.List;

/* default */ interface PaymentMethods {

    /* default */ interface View extends MvpView {
        void showPaymentMethods(final List<PaymentMethod> paymentMethods);

        void showProgress();

        void hideProgress();

        void showError(final MercadoPagoError exception);

        void showBankDeals();
    }

    /* default */ interface Actions {

        void setShowBankDeals(final boolean showBankDeals);

        void setPaymentPreference(final PaymentPreference paymentPreference);

        void setSupportedPaymentTypes(final List<String> supportedPaymentTypes);

        void start();

        void recoverFromFailure();
    }
}
