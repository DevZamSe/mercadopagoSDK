package com.mercadopago.android.px.internal.viewmodel.mappers;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.repository.AmountConfigurationRepository;
import com.mercadopago.android.px.internal.repository.DisabledPaymentMethodRepository;
import com.mercadopago.android.px.internal.view.PaymentMethodDescriptorView;
import com.mercadopago.android.px.internal.viewmodel.AccountMoneyDescriptorModel;
import com.mercadopago.android.px.internal.viewmodel.CreditCardDescriptorModel;
import com.mercadopago.android.px.internal.viewmodel.DebitCardDescriptorModel;
import com.mercadopago.android.px.internal.viewmodel.EmptyInstallmentsDescriptorModel;
import com.mercadopago.android.px.model.Currency;
import com.mercadopago.android.px.model.ExpressMetadata;
import com.mercadopago.android.px.model.PaymentTypes;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDescriptorMapper
    extends Mapper<List<ExpressMetadata>, List<PaymentMethodDescriptorView.Model>> {

    @NonNull private final Currency currency;
    @NonNull private final AmountConfigurationRepository amountConfigurationRepository;
    @NonNull private final DisabledPaymentMethodRepository disabledPaymentMethodRepository;

    public PaymentMethodDescriptorMapper(@NonNull final Currency currency,
        @NonNull final AmountConfigurationRepository amountConfigurationRepository,
        @NonNull final DisabledPaymentMethodRepository disabledPaymentMethodRepository) {
        this.currency = currency;
        this.amountConfigurationRepository = amountConfigurationRepository;
        this.disabledPaymentMethodRepository = disabledPaymentMethodRepository;
    }

    @Override
    public List<PaymentMethodDescriptorView.Model> map(@NonNull final List<ExpressMetadata> expressMetadataList) {
        final List<PaymentMethodDescriptorView.Model> models = new ArrayList<>();

        for (final ExpressMetadata expressMetadata : expressMetadataList) {
            models.add(createInstallmentsDescriptorModel(expressMetadata));
        }
        //Last card is Add new payment method card
        models.add(createAddNewPaymentModel());

        return models;
    }

    private PaymentMethodDescriptorView.Model createInstallmentsDescriptorModel(final ExpressMetadata expressMetadata) {
        final String paymentTypeId = expressMetadata.getPaymentTypeId();
        final String customOptionId =
            expressMetadata.isCard() ? expressMetadata.getCard().getId() : expressMetadata.getPaymentMethodId();

        if (PaymentTypes.isCreditCardPaymentType(paymentTypeId) || expressMetadata.isConsumerCredits()) {
            //This model is useful for Credit Card only
            // FIXME change model to represent more than just credit cards.
            return CreditCardDescriptorModel
                .createFrom(currency, amountConfigurationRepository.getConfigurationFor(customOptionId),
                    disabledPaymentMethodRepository.hasPaymentMethodId(customOptionId));
        } else if (PaymentTypes.isCardPaymentType(paymentTypeId)) {
            return DebitCardDescriptorModel
                .createFrom(currency, amountConfigurationRepository.getConfigurationFor(customOptionId),
                    disabledPaymentMethodRepository.hasPaymentMethodId(customOptionId));
        } else if (PaymentTypes.isAccountMoney(expressMetadata.getPaymentMethodId())) {
            return AccountMoneyDescriptorModel.createFrom(expressMetadata.getAccountMoney(),
                disabledPaymentMethodRepository.hasPaymentMethodId(customOptionId));
        } else {
            return EmptyInstallmentsDescriptorModel.create();
        }
    }

    private PaymentMethodDescriptorView.Model createAddNewPaymentModel() {
        return EmptyInstallmentsDescriptorModel.create();
    }
}
