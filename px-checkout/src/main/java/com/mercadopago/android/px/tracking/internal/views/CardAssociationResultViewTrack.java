package com.mercadopago.android.px.tracking.internal.views;

import android.support.annotation.NonNull;
import java.util.Locale;

public class CardAssociationResultViewTrack extends ViewTracker {
    private static final String PATH = BASE_VIEW_PATH + "/card_association_result/%s";

    @NonNull private final Type type;

    public CardAssociationResultViewTrack(@NonNull final Type type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String getViewPath() {
        return String.format(Locale.US, PATH, type.value);
    }

    public enum Type {
        SUCCESS("success"),
        ERROR("error");

        private final String value;

        Type(final String value) {

            this.value = value;
        }
    }
}
