package com.mercadopago.android.px.internal.viewmodel;

import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.internal.view.PaymentMethodDescriptorView;

public class EmptyInstallmentsDescriptorModel extends PaymentMethodDescriptorView.Model {

    public static PaymentMethodDescriptorView.Model create() {
        return new EmptyInstallmentsDescriptorModel();
    }

    @Override
    public void updateSpannable(@NonNull final SpannableStringBuilder spannableStringBuilder,
        @NonNull final TextView textView) {
        spannableStringBuilder.append(TextUtil.SPACE);
    }
}
