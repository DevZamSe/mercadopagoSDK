package com.mercadopago.android.px.internal.features.express.add_new_card;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.internal.base.BasePresenter;
import com.mercadopago.android.px.internal.repository.InitRepository;
import com.mercadopago.android.px.model.PaymentMethodSearchItem;
import com.mercadopago.android.px.model.exceptions.ApiException;
import com.mercadopago.android.px.model.internal.InitResponse;
import com.mercadopago.android.px.services.Callback;
import java.util.List;

public class AddNewCardPresenter extends BasePresenter<AddNewCard.View> implements AddNewCard.Actions {

    private static final String TYPE_TO_DRIVE = "cards";

    private final InitRepository initRepository;

    /* default */ AddNewCardPresenter(@NonNull final InitRepository initRepository) {
        this.initRepository = initRepository;
    }

    @Override
    public void onAddNewCardSelected() {
        initRepository.init().execute(new Callback<InitResponse>() {
            @Override
            public void success(final InitResponse initResponse) {
                final PaymentMethodSearchItem paymentMethodSearchItem = getCardsGroup(initResponse.getGroups());
                if (paymentMethodSearchItem != null) {
                    getView().showPaymentMethodsWithSelection(paymentMethodSearchItem);
                } else {
                    getView().showPaymentMethods();
                }
            }

            @Override
            public void failure(final ApiException apiException) {
                throw new IllegalStateException("AddNewCardPresenter could not retrieve PaymentMethodSearch");
            }
        });
    }

    @Nullable
        /* default */ PaymentMethodSearchItem getCardsGroup(@NonNull final List<PaymentMethodSearchItem> groups) {
        for (final PaymentMethodSearchItem paymentMethodSearchItem : groups) {
            if (TYPE_TO_DRIVE.equalsIgnoreCase(paymentMethodSearchItem.getId())) {
                return paymentMethodSearchItem;
            }
        }
        return null;
    }
}