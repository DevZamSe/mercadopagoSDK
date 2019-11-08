package com.mercadopago.android.px.model.display_info;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public final class LinkablePhrase implements Parcelable, Serializable {

    private final String phrase;
    private final String textColor;
    private final String link;
    private final String html;

    public String getPhrase() {
        return phrase;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getLink() {
        return link;
    }

    public String getHtml() {
        return html;
    }

    protected LinkablePhrase(final Parcel in) {
        phrase = in.readString();
        textColor = in.readString();
        link = in.readString();
        html = in.readString();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(phrase);
        dest.writeString(textColor);
        dest.writeString(link);
        dest.writeString(html);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LinkablePhrase> CREATOR = new Creator<LinkablePhrase>() {
        @Override
        public LinkablePhrase createFromParcel(final Parcel in) {
            return new LinkablePhrase(in);
        }

        @Override
        public LinkablePhrase[] newArray(final int size) {
            return new LinkablePhrase[size];
        }
    };
}