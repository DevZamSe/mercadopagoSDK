package com.mercadopago.android.px.internal.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface IDetailDrawable {

    @Nullable
    Drawable getDrawable(@NonNull final Context context);
}
