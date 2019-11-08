package com.mercadopago.android.px.utils;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import com.mercadopago.SampleDialog;
import com.mercadopago.SamplePaymentProcessor;
import com.mercadopago.android.px.configuration.PaymentConfiguration;
import com.mercadopago.android.px.core.DynamicDialogCreator;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.BusinessPayment;
import com.mercadopago.android.px.model.PaymentTypes;
import com.mercadopago.android.px.model.commission.PaymentTypeChargeRule;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

final class ChargesSamples {

    private static final String PK = "TEST-2b85324f-35e4-4ad6-a486-1135c621cfae";
    private static final String PREF = "\"161054091-e29b9601-258f-4867-80c0-52304d9f11fd";

    private ChargesSamples() {
    }

    public static void addAll(final Collection<Pair<String, MercadoPagoCheckout.Builder>> options) {
        options.add(new Pair<>("Extra charges - CreditCard", chargeType(PaymentTypes.CREDIT_CARD)));
    }

    private static MercadoPagoCheckout.Builder chargeType(final String type) {
        final Collection<PaymentTypeChargeRule> charges = new ArrayList<>();
        charges.add(new PaymentTypeChargeRule(type, BigDecimal.TEN, new DynamicDialogCreator() {
            @Override
            public boolean shouldShowDialog(@NonNull final Context context, @NonNull final CheckoutData checkoutData) {
                return true;
            }

            @NonNull
            @Override
            public DialogFragment create(@NonNull final Context context, @NonNull final CheckoutData checkoutData) {
                return new SampleDialog();
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(final Parcel parcel, final int i) {

            }
        }));

        final BusinessPayment payment = BusinessSamples.getBusinessRejected();

        return new MercadoPagoCheckout.Builder(PK, PREF,
            new PaymentConfiguration.Builder(new SamplePaymentProcessor(payment))
                .addChargeRules(charges)
                .build());
    }
}