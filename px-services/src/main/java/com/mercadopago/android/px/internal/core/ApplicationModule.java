package com.mercadopago.android.px.internal.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.util.RetrofitUtil;
import java.io.File;
import retrofit2.Retrofit;

public class ApplicationModule implements PreferenceComponent {

    private static final String SHARED_PREFERENCE_NAME = "com.mercadopago.checkout.store";

    @NonNull
    private Context applicationContext;

    public ApplicationModule(@NonNull final Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @NonNull
    public Context getApplicationContext() {
        return applicationContext;
    }

    @NonNull
    public SessionIdProvider getSessionIdProvider() {
        return new SessionIdProvider(getSharedPreferences());
    }

    @NonNull
    public SessionIdProvider newSessionProvider(@NonNull final String sessionId) {
        return new SessionIdProvider(getSharedPreferences(), sessionId);
    }

    @NonNull
    public ProductIdProvider getProductIdProvider() {
        return new ProductIdProvider(getSharedPreferences());
    }

    @NonNull
    public ProductIdProvider newProductIdProvider(@NonNull final String productId) {
        return new ProductIdProvider(getSharedPreferences(), productId);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return applicationContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public FileManager getFileManager() {
        return new FileManager();
    }

    public File getCacheDir() {
        return applicationContext.getCacheDir();
    }

    public Retrofit getRetrofitClient() {
        return RetrofitUtil.getRetrofitClient(applicationContext);
    }
}
