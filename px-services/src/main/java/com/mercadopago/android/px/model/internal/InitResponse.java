package com.mercadopago.android.px.model.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.model.Currency;
import com.mercadopago.android.px.model.PaymentMethodSearch;
import com.mercadopago.android.px.model.Site;
import com.mercadopago.android.px.preferences.CheckoutPreference;
import java.util.List;

public final class InitResponse extends PaymentMethodSearch {

    private CheckoutPreference preference;

    private Site site;

    private Currency currency;

    private List<Experiment> experiments;

    @Nullable
    public CheckoutPreference getCheckoutPreference() {
        return preference;
    }

    @NonNull
    public Site getSite() {
        return site;
    }

    @NonNull
    public Currency getCurrency() {
        return currency;
    }

    @Nullable
    public List<Experiment> getExperiments() {
        return experiments;
    }
}