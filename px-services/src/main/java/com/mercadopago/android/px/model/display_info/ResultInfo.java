package com.mercadopago.android.px.model.display_info;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public final class ResultInfo implements Parcelable, Serializable {

    private final String title;
    private final String subtitle;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public static final Creator<ResultInfo> CREATOR = new Creator<ResultInfo>() {
        @Override
        public ResultInfo createFromParcel(final Parcel in) {
            return new ResultInfo(in);
        }

        @Override
        public ResultInfo[] newArray(final int size) {
            return new ResultInfo[size];
        }
    };

    protected ResultInfo(final Parcel in) {
        title = in.readString();
        subtitle = in.readString();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(title);
        dest.writeString(subtitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}