package com.mercadopago.android.px.internal.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.di.Session;

public class TotalLocalized implements ILocalizedCharSequence {

    @Override
    public CharSequence get(@NonNull final Context context) {
        final Resources resources = context.getResources();
        final int mainVerbStringResourceId =
            Session.getInstance().getMainVerb();
        final String verb = resources.getString(mainVerbStringResourceId);
        return resources.getString(R.string.px_total_to_pay, verb);
    }
}
