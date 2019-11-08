package com.mercadopago.android.px.model.requests;

import com.google.gson.annotations.SerializedName;

/**
 * there is no use for this, it was used on payment_methods request.
 */
@Deprecated
public class GroupsIntent {

    @SerializedName("access_token")
    private String privateKey;

    public GroupsIntent(final String privateKey) {
        this.privateKey = privateKey;
    }
}
