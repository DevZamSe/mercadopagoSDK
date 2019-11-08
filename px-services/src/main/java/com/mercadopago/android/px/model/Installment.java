package com.mercadopago.android.px.model;

import java.util.List;

public class Installment {

    @Deprecated private Issuer issuer;
    private List<PayerCost> payerCosts;
    @Deprecated private String paymentMethodId;
    @Deprecated private String paymentTypeId;

    public List<PayerCost> getPayerCosts() {
        return payerCosts;
    }

    @Deprecated
    public Issuer getIssuer() {
        return issuer;
    }

    @Deprecated
    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    @Deprecated
    public void setPayerCosts(List<PayerCost> payerCosts) {
        this.payerCosts = payerCosts;
    }

    @Deprecated
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    @Deprecated
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Deprecated
    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    @Deprecated
    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }
}