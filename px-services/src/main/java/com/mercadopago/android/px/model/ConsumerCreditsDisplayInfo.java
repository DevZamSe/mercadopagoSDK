package com.mercadopago.android.px.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.mercadopago.android.px.model.display_info.LinkableText;
import java.io.Serializable;

public class ConsumerCreditsDisplayInfo implements Parcelable, Serializable {

    public final String color;
    public final String fontColor;
    public final LinkableText topText;
    public final LinkableText bottomText;

    protected ConsumerCreditsDisplayInfo(final Parcel in) {
        color = in.readString();
        fontColor = in.readString();
        topText = in.readParcelable(LinkableText.class.getClassLoader());
        bottomText = in.readParcelable(LinkableText.class.getClassLoader());
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(color);
        dest.writeString(fontColor);
        dest.writeParcelable(topText, flags);
        dest.writeParcelable(bottomText, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ConsumerCreditsDisplayInfo> CREATOR = new Creator<ConsumerCreditsDisplayInfo>() {
        @Override
        public ConsumerCreditsDisplayInfo createFromParcel(final Parcel in) {
            return new ConsumerCreditsDisplayInfo(in);
        }

        @Override
        public ConsumerCreditsDisplayInfo[] newArray(final int size) {
            return new ConsumerCreditsDisplayInfo[size];
        }
    };
}
