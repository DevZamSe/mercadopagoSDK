package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.model.Issuer;
import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.tracking.internal.mapper.FromPaymentMethodToAvailableMethods;
import com.mercadopago.android.px.tracking.internal.model.AvailableBanks;
import java.util.List;
import java.util.Map;

public class IssuersViewTrack extends ViewTracker {

    private static final String PATH = BASE_VIEW_PATH + PAYMENTS_PATH + "/card_issuer";

    @NonNull private final List<Issuer> issuers;
    @NonNull private final PaymentMethod paymentMethod;

    public IssuersViewTrack(@NonNull final List<Issuer> issuers,
        @NonNull final PaymentMethod paymentMethod) {
        this.issuers = issuers;
        this.paymentMethod = paymentMethod;
    }

    @NonNull
    @Override
    public Map<String, Object> getData() {
        final Map<String, Object> data = super.getData();
        data.putAll(new AvailableBanks(issuers).toMap());
        data.putAll(new FromPaymentMethodToAvailableMethods().map(paymentMethod).toMap());
        return data;
    }

    @NonNull
    @Override
    public String getViewPath() {
        return PATH;
    }
}
