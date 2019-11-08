package com.mercadopago.android.px.internal.features.review_payment_methods;

import com.mercadopago.android.px.internal.base.BasePresenter;
import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.tracking.internal.views.ErrorMoreInfoCardViewTracker;
import java.util.List;

/* default */ class ReviewPaymentMethodsPresenter
    extends BasePresenter<ReviewPaymentMethods.View> implements ReviewPaymentMethods.Actions {

    private final List<PaymentMethod> supportedPaymentMethods;

    public ReviewPaymentMethodsPresenter(final List<PaymentMethod> supportedPaymentMethods) {
        this.supportedPaymentMethods = supportedPaymentMethods;
    }

    @Override
    public void initialize() {
        setCurrentViewTracker(new ErrorMoreInfoCardViewTracker());
        getView().initializeSupportedPaymentMethods(supportedPaymentMethods);
    }
}
