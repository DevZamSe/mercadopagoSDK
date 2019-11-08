package com.mercadopago.android.px.internal.core;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public final class ProductIdProvider {

    public static final String DEFAULT_PRODUCT_ID = "BJEO9NVBF6RG01IIIOTG";
    private static final String PREF_PRODUCT_ID = "PREF_HEADER_PRODUCT_ID";

    @NonNull private final SharedPreferences sharedPreferences;

    /* default */ ProductIdProvider(@NonNull final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /* default */ ProductIdProvider(@NonNull final SharedPreferences sharedPreferences,
        @NonNull final String newProductId) {
        this.sharedPreferences = sharedPreferences;
        this.sharedPreferences.edit().putString(PREF_PRODUCT_ID, newProductId).apply();
    }

    @NonNull
    public String getProductId() {
        return sharedPreferences.getString(PREF_PRODUCT_ID, DEFAULT_PRODUCT_ID);
    }
}