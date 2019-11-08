package com.mercadopago.android.px.internal.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.model.Currency;
import com.mercadopago.android.px.model.IPaymentDescriptor;
import com.mercadopago.android.px.model.PaymentResult;
import com.mercadopago.android.px.model.internal.PaymentReward;

public class PaymentModel implements Parcelable {

    public static final Creator<PaymentModel> CREATOR = new Creator<PaymentModel>() {
        @Override
        public PaymentModel createFromParcel(final Parcel in) {
            return new PaymentModel(in);
        }

        @Override
        public PaymentModel[] newArray(final int size) {
            return new PaymentModel[size];
        }
    };

    private final IPaymentDescriptor payment;
    private final PaymentResult paymentResult;
    private final PaymentReward paymentReward;
    private final Currency currency;

    public PaymentModel(@Nullable final IPaymentDescriptor payment, @NonNull final PaymentResult paymentResult,
        @NonNull final PaymentReward paymentReward, @NonNull final Currency currency) {
        this.payment = payment;
        this.paymentResult = paymentResult;
        this.paymentReward = paymentReward;
        this.currency = currency;
    }

    @Nullable
    public IPaymentDescriptor getPayment() {
        return payment;
    }

    @NonNull
    public PaymentResult getPaymentResult() {
        return paymentResult;
    }

    @NonNull
    public Currency getCurrency() {
        return currency;
    }

    @NonNull
    public PaymentReward getPaymentReward() {
        return paymentReward;
    }

    /* default */ PaymentModel(final Parcel in) {
        payment = (IPaymentDescriptor) in.readSerializable();
        paymentResult = (PaymentResult) in.readSerializable();
        paymentReward = in.readParcelable(PaymentReward.class.getClassLoader());
        currency = in.readParcelable(Currency.class.getClassLoader());
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeSerializable(payment);
        dest.writeSerializable(paymentResult);
        dest.writeParcelable(paymentReward, flags);
        dest.writeParcelable(currency, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}