package com.mercadopago.android.px.internal.viewmodel.drawables;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.mercadopago.android.px.model.ConsumerCreditsMetadata;

public class ConsumerCreditsDrawableFragmentItem extends DrawableFragmentItem implements Parcelable {

    @NonNull public final ConsumerCreditsMetadata metadata;

    public ConsumerCreditsDrawableFragmentItem(@NonNull final ConsumerCreditsMetadata metadata) {
        this.metadata = metadata;
    }

    protected ConsumerCreditsDrawableFragmentItem(final Parcel in) {
        metadata = in.readParcelable(ConsumerCreditsMetadata.class.getClassLoader());
    }

    @Override
    public Fragment draw(@NonNull final PaymentMethodFragmentDrawer drawer) {
        return drawer.draw(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(metadata, flags);
    }

    public static final Creator<ConsumerCreditsDrawableFragmentItem> CREATOR =
        new Creator<ConsumerCreditsDrawableFragmentItem>() {
            @Override
            public ConsumerCreditsDrawableFragmentItem createFromParcel(Parcel in) {
                return new ConsumerCreditsDrawableFragmentItem(in);
            }

            @Override
            public ConsumerCreditsDrawableFragmentItem[] newArray(int size) {
                return new ConsumerCreditsDrawableFragmentItem[size];
            }
        };

}