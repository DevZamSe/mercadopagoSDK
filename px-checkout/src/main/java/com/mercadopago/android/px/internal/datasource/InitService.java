package com.mercadopago.android.px.internal.datasource;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.addons.ESCManagerBehaviour;
import com.mercadopago.android.px.configuration.AdvancedConfiguration;
import com.mercadopago.android.px.configuration.DiscountParamsConfiguration;
import com.mercadopago.android.px.configuration.PaymentConfiguration;
import com.mercadopago.android.px.internal.callbacks.MPCall;
import com.mercadopago.android.px.internal.datasource.cache.Cache;
import com.mercadopago.android.px.internal.repository.InitRepository;
import com.mercadopago.android.px.internal.repository.PaymentSettingRepository;
import com.mercadopago.android.px.internal.repository.ExperimentsRepository;
import com.mercadopago.android.px.internal.services.CheckoutService;
import com.mercadopago.android.px.internal.util.JsonUtil;
import com.mercadopago.android.px.model.exceptions.ApiException;
import com.mercadopago.android.px.model.internal.CheckoutFeatures;
import com.mercadopago.android.px.model.internal.InitRequest;
import com.mercadopago.android.px.model.internal.InitResponse;
import com.mercadopago.android.px.preferences.CheckoutPreference;
import com.mercadopago.android.px.services.Callback;
import com.mercadopago.android.px.tracking.internal.MPTracker;
import java.util.ArrayList;

import static com.mercadopago.android.px.services.BuildConfig.API_ENVIRONMENT_INIT;

public class InitService implements InitRepository {

    @NonNull private final ESCManagerBehaviour escManagerBehaviour;
    @NonNull private final CheckoutService checkoutService;
    @NonNull private final String language;
    @NonNull /* default */ final PaymentSettingRepository paymentSettingRepository;
    @NonNull /* default */ final ExperimentsRepository experimentsRepository;
    @NonNull /* default */ final Cache<InitResponse> initCache;
    @Nullable private final String flow;

    public InitService(@NonNull final PaymentSettingRepository paymentSettingRepository,
        @NonNull final ExperimentsRepository experimentsRepository,
        @NonNull final ESCManagerBehaviour escManagerBehaviour, @NonNull final CheckoutService checkoutService,
        @NonNull final String language, @Nullable final String flow, @NonNull final Cache<InitResponse> initCache) {
        this.paymentSettingRepository = paymentSettingRepository;
        this.experimentsRepository = experimentsRepository;
        this.escManagerBehaviour = escManagerBehaviour;
        this.checkoutService = checkoutService;
        this.language = language;
        this.flow = flow;
        this.initCache = initCache;
    }

    @NonNull
    @Override
    public MPCall<InitResponse> init() {
        if (initCache.isCached()) {
            return initCache.get();
        } else {
            return newCall();
        }
    }

    @NonNull
    private MPCall<InitResponse> newCall() {
        return new MPCall<InitResponse>() {

            @Override
            public void enqueue(final Callback<InitResponse> callback) {
                newRequest().enqueue(getInternalCallback(callback));
            }

            @Override
            public void execute(final Callback<InitResponse> callback) {
                newRequest().execute(getInternalCallback(callback));
            }

            @NonNull /* default */ Callback<InitResponse> getInternalCallback(
                final Callback<InitResponse> callback) {
                return new Callback<InitResponse>() {
                    @Override
                    public void success(final InitResponse initResponse) {
                        MPTracker.getInstance().hasExpressCheckout(initResponse.hasExpressCheckoutMetadata());
                        if (initResponse.getCheckoutPreference() != null) {
                            paymentSettingRepository.configure(initResponse.getCheckoutPreference());
                        }
                        paymentSettingRepository.configure(initResponse.getSite());
                        paymentSettingRepository.configure(initResponse.getCurrency());
                        experimentsRepository.configure(initResponse.getExperiments());

                        MPTracker.getInstance().setExperiments(experimentsRepository.getExperiments());

                        initCache.put(initResponse);
                        callback.success(initResponse);
                    }

                    @Override
                    public void failure(final ApiException apiException) {
                        callback.failure(apiException);
                    }
                };
            }
        };
    }

    @NonNull
        /* default */ MPCall<InitResponse> newRequest() {
        final CheckoutPreference checkoutPreference = paymentSettingRepository.getCheckoutPreference();
        final PaymentConfiguration paymentConfiguration = paymentSettingRepository.getPaymentConfiguration();

        final AdvancedConfiguration advancedConfiguration = paymentSettingRepository.getAdvancedConfiguration();
        final DiscountParamsConfiguration discountParamsConfiguration =
            advancedConfiguration
                .getDiscountParamsConfiguration();

        final CheckoutFeatures features = new CheckoutFeatures.Builder()
            .setSplit(paymentConfiguration.getPaymentProcessor().supportsSplitPayment(checkoutPreference))
            .setExpress(advancedConfiguration.isExpressPaymentEnabled())
            .build();

        final InitRequest initRequest = new InitRequest.Builder(paymentSettingRepository.getPublicKey())
            .setCardWithEsc(new ArrayList<>(escManagerBehaviour.getESCCardIds()))
            .setCharges(paymentConfiguration.getCharges())
            .setDiscountParamsConfiguration(discountParamsConfiguration)
            .setCheckoutFeatures(features)
            .setCheckoutPreference(checkoutPreference)
            .setFlow(flow)
            .build();

        final String preferenceId = paymentSettingRepository.getCheckoutPreferenceId();
        if (preferenceId != null) {
            return checkoutService.checkout(API_ENVIRONMENT_INIT, preferenceId, language,
                paymentSettingRepository.getPrivateKey(), JsonUtil.getMapFromObject(initRequest));
        } else {
            return checkoutService.checkout(API_ENVIRONMENT_INIT, language,
                paymentSettingRepository.getPrivateKey(), JsonUtil.getMapFromObject(initRequest));
        }
    }
}