package com.mercadopago.android.px.internal.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.mercadopago.android.px.R;

public final class SummaryViewDefaultColor implements IDetailColor {

    @Override
    public int getColor(@NonNull final Context context) {
        return ContextCompat.getColor(context, R.color.px_expressCheckoutTextColor);
    }
}