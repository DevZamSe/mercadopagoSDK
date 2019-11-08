package com.mercadopago.android.px.tracking.internal.model;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import com.mercadopago.android.px.model.PayerCost;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Keep
public class PayerCostInfoList extends TrackingMapModel {

    @NonNull private final List<PayerCostInfo> availableInstallments;

    public PayerCostInfoList(@NonNull final Iterable<PayerCost> payerCosts) {
        availableInstallments = new ArrayList<>();
        for (final PayerCost payerCost : payerCosts) {
            availableInstallments.add(new PayerCostInfo(payerCost));
        }
    }
}
