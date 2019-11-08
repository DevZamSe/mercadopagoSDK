package com.mercadopago.android.px.model.display_info;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.List;

public final class LinkableText implements Parcelable, Serializable {

    private final String text;
    private final String textColor;
    private final List<LinkablePhrase> linkablePhrases;

    public List<LinkablePhrase> getLinkablePhrases() {
        return linkablePhrases;
    }

    public String getText() {
        return text;
    }

    public String getTextColor() {
        return textColor;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(text);
        dest.writeString(textColor);
        dest.writeTypedList(linkablePhrases);
    }

    protected LinkableText(final Parcel in) {
        text = in.readString();
        textColor = in.readString();
        linkablePhrases = in.createTypedArrayList(LinkablePhrase.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LinkableText> CREATOR = new Creator<LinkableText>() {
        @Override
        public LinkableText createFromParcel(final Parcel in) {
            return new LinkableText(in);
        }

        @Override
        public LinkableText[] newArray(final int size) {
            return new LinkableText[size];
        }
    };
}