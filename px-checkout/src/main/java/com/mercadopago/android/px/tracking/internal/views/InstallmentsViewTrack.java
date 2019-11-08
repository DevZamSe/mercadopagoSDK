package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.internal.repository.UserSelectionRepository;
import com.mercadopago.android.px.model.Card;
import com.mercadopago.android.px.model.Issuer;
import com.mercadopago.android.px.model.PayerCost;
import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.tracking.internal.mapper.FromPaymentMethodToAvailableMethods;
import com.mercadopago.android.px.tracking.internal.model.PayerCostInfoList;
import java.util.List;
import java.util.Map;

public class InstallmentsViewTrack extends ViewTracker {

    private static final String PATH = BASE_VIEW_PATH + PAYMENTS_PATH + "/installments";

    @NonNull private final List<PayerCost> payerCosts;

    //Saved card
    @Nullable private final Card card;
    @Nullable private final Issuer issuer;
    @Nullable private final PaymentMethod paymentMethod;

    public InstallmentsViewTrack(@NonNull final List<PayerCost> payerCosts,
        @NonNull final UserSelectionRepository userSelectionRepository) {
        this.payerCosts = payerCosts;
        card = userSelectionRepository.getCard();
        paymentMethod = userSelectionRepository.getPaymentMethod();
        issuer = userSelectionRepository.getIssuer();
    }

    @NonNull
    @Override
    public Map<String, Object> getData() {
        final Map<String, Object> data = super.getData();
        if (paymentMethod != null) {
            data.putAll(new FromPaymentMethodToAvailableMethods().map(paymentMethod).toMap());
        }

        if (card != null) {
            data.put("card_id", card.getId());
        }
        if (issuer != null) {
            data.put("issuer_id", issuer.getId());
        }

        data.putAll(new PayerCostInfoList(payerCosts).toMap());

        return data;
    }

    @NonNull
    @Override
    public String getViewPath() {
        return PATH;
    }
}
