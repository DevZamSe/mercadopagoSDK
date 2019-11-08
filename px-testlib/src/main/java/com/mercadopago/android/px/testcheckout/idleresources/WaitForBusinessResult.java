package com.mercadopago.android.px.testcheckout.idleresources;

import android.support.test.espresso.IdlingRegistry;
import com.mercadopago.android.px.internal.features.business_result.BusinessPaymentResultActivity;
import com.mercadopago.android.testlib.idlingresource.ActivityIdlingResource;

public class WaitForBusinessResult {

    private final IdlingRegistry register;
    private final ActivityIdlingResource activityIdlingResource;

    public WaitForBusinessResult() {
        register = IdlingRegistry.getInstance();
        activityIdlingResource = new ActivityIdlingResource(BusinessPaymentResultActivity.class);
    }

    public void start() {
        //Here wait for activity while animation exploding button starts.
        register.register(activityIdlingResource);
    }

    public void stop() {
        register.unregister(activityIdlingResource);
    }
}
