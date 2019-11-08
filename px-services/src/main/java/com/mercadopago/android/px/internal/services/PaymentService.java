package com.mercadopago.android.px.internal.services;

import com.mercadopago.android.px.internal.callbacks.MPCall;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.PaymentMethod;
import java.util.List;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PaymentService {

    String PAYMENTS_VERSION = "2.0";

    @GET("{environment}/px_mobile_api/payment_methods/cards")
    MPCall<List<PaymentMethod>> getCardPaymentMethods(
        @Path(value = "environment", encoded = true) String environment,
        @Query("access_token") String accessToken);

    @POST("{environment}/px_mobile/payments?api_version=" + PAYMENTS_VERSION)
    MPCall<Payment> createPayment(
        @Path(value = "environment", encoded = true) String environment,
        @Header("X-Idempotency-Key") String transactionId, @Body Map<String, Object> additionalInfo,
        @QueryMap Map<String, String> query);
}