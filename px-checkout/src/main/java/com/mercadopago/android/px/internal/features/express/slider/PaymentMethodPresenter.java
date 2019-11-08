package com.mercadopago.android.px.internal.features.express.slider;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.base.BasePresenter;
import com.mercadopago.android.px.internal.repository.DisabledPaymentMethodRepository;
import com.mercadopago.android.px.internal.viewmodel.drawables.DrawableFragmentItem;

class PaymentMethodPresenter extends BasePresenter<PaymentMethod.View> implements PaymentMethod.Action {
    private final DisabledPaymentMethodRepository disabledPaymentMethodRepository;
    private final DrawableFragmentItem item;

    /* default */ PaymentMethodPresenter(@NonNull final DisabledPaymentMethodRepository disabledPaymentMethodRepository,
        @NonNull final DrawableFragmentItem item) {
        this.disabledPaymentMethodRepository = disabledPaymentMethodRepository;
        this.item = item;
    }

    @Override
    public void onViewResumed() {
        if (disabledPaymentMethodRepository.hasPaymentMethodId(item.getId())) {
            getView().disable();
        }
    }
}