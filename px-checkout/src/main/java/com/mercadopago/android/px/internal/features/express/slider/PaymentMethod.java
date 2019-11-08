package com.mercadopago.android.px.internal.features.express.slider;

import com.mercadopago.android.px.internal.base.MvpView;

public interface PaymentMethod {
    interface View extends MvpView {
        void disable();
    }

    interface Action {
        void onViewResumed();
    }
}