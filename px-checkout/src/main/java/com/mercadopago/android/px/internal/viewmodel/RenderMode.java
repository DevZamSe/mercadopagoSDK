package com.mercadopago.android.px.internal.viewmodel;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
    RenderMode.HIGH_RES,
    RenderMode.LOW_RES
})
public @interface RenderMode {
    String HIGH_RES = "high_res";
    String LOW_RES = "low_res";
}