package com.mercadopago.android.px.internal.features.express.slider;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.mercadopago.android.px.internal.features.express.add_new_card.AddNewCardLowResFragment;
import com.mercadopago.android.px.internal.viewmodel.drawables.AccountMoneyDrawableFragmentItem;
import com.mercadopago.android.px.internal.viewmodel.drawables.AddNewCardFragmentDrawableFragmentItem;
import com.mercadopago.android.px.internal.viewmodel.drawables.ConsumerCreditsDrawableFragmentItem;
import com.mercadopago.android.px.internal.viewmodel.drawables.DrawableFragmentItem;
import com.mercadopago.android.px.internal.viewmodel.drawables.PaymentMethodFragmentDrawer;
import com.mercadopago.android.px.internal.viewmodel.drawables.SavedCardDrawableFragmentItem;

/* default */ class PaymentMethodLowResDrawer implements PaymentMethodFragmentDrawer {

    @Override
    public Fragment draw(@NonNull final DrawableFragmentItem drawableFragmentItem) {
        throw new IllegalStateException("Unknown type - PaymentMethodFragmentAdapter");
    }

    @Override
    public Fragment draw(@NonNull final AddNewCardFragmentDrawableFragmentItem drawableItem) {
        return AddNewCardLowResFragment.getInstance(drawableItem);
    }

    @Override
    public Fragment draw(@NonNull final SavedCardDrawableFragmentItem drawableItem) {
        return SavedCardLowResFragment.getInstance(drawableItem);
    }

    @Override
    public Fragment draw(@NonNull final AccountMoneyDrawableFragmentItem drawableItem) {
        return AccountMoneyLowResFragment.getInstance(drawableItem);
    }

    @Override
    public Fragment draw(@NonNull final ConsumerCreditsDrawableFragmentItem drawableItem) {
        return ConsumerCreditsLowResFragment.getInstance(drawableItem);
    }
}