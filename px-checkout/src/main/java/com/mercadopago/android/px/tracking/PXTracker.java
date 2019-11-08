package com.mercadopago.android.px.tracking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.tracking.internal.MPTracker;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class PXTracker {

    private PXTracker() {
    }

    /**
     * Set your own tracker listener to be aware of PX - Checkout events.
     *
     * @param listener your listener.
     * @deprecated Deprecated due to new tracking implementation standards. Use {@link com.mercadopago.android.px.tracking.PXTracker#setListener(PXTrackingListener)}
     * instead.
     */
    @Deprecated
    public static void setListener(@Nullable final PXEventListener listener) {
        MPTracker.getInstance().setTracksListener(listener);
    }

    /**
     * Set your own tracker listener to be aware of PX - Checkout events.
     *
     * @param listener your listener.
     */
    public static void setListener(@Nullable final PXTrackingListener listener) {
        PXTracker.setListener(listener, new HashMap<String, Object>(), null);
    }

    /**
     * Set your own tracker listener to be aware of PX - Checkout events.
     *
     * @param listener your listener.
     */
    public static void setListener(@Nullable final PXTrackingListener listener,
        @NonNull final Map<String, ? extends Object> flowDetail, @Nullable final String flowName) {
        MPTracker.getInstance().setPXTrackingListener(listener);
        MPTracker.getInstance().setFlowDetail(flowDetail);
        MPTracker.getInstance().setFlowName(flowName);
    }
}