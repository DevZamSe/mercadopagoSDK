package com.mercadopago.android.px.model.internal;

import java.util.List;

public class Variant {

    private String id;
    private String name;
    private List<AvailableFeature> availableFeatures;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AvailableFeature> getAvailableFeatures() {
        return availableFeatures;
    }
}
