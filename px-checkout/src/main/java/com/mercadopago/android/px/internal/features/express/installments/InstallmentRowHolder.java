package com.mercadopago.android.px.internal.features.express.installments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.util.CurrenciesUtil;
import com.mercadopago.android.px.model.Currency;
import com.mercadopago.android.px.model.PayerCost;
import com.mercadopago.android.px.model.Site;
import java.math.BigDecimal;
import java.util.Locale;

//TODO unify with normal installments.

/* default */ class InstallmentRowHolder extends RecyclerView.ViewHolder {

    private final TextView installmentsTextView;
    private final TextView zeroRateText;
    private final TextView totalText;
    private final View highlight;

    /* default */ InstallmentRowHolder(final View itemView) {
        super(itemView);
        installmentsTextView = itemView.findViewById(R.id.mpsdkInstallmentsText);
        zeroRateText = itemView.findViewById(R.id.mpsdkInstallmentsZeroRate);
        totalText = itemView.findViewById(R.id.mpsdkInstallmentsWithRate);
        highlight = itemView.findViewById(R.id.highlight);
    }

    /* default */ void populate(final InstallmentsAdapter.ItemListener itemListener, @NonNull final Site site,
        @NonNull final Currency currency, @NonNull final PayerCost payerCost) {
        setInstallmentsText(currency, payerCost);

        if (!site.shouldWarnAboutBankInterests()) {
            if (BigDecimal.ZERO.equals(payerCost.getInstallmentRate())) {
                totalText.setVisibility(View.GONE);
                zeroRateText.setVisibility(payerCost.getInstallments() > 1 ? View.VISIBLE : View.GONE);
            } else {
                zeroRateText.setVisibility(View.GONE);
                setAmountWithRateText(currency, payerCost);
            }
        }

        itemView.setOnClickListener(v -> itemListener.onClick(payerCost));
    }

    private void setAmountWithRateText(@NonNull final Currency currency, @NonNull final PayerCost payerCost) {
        totalText.setVisibility(View.VISIBLE);

        final Spanned spannedInstallmentsText =
            CurrenciesUtil.getSpannedAmountWithCurrencySymbol(payerCost.getTotalAmount(), currency);

        totalText.setText(TextUtils.concat("(", spannedInstallmentsText, ")"));
    }

    private void setInstallmentsText(@NonNull final Currency currency, @NonNull final PayerCost payerCost) {

        final Spanned spannedInstallmentsText =
            CurrenciesUtil.getSpannedAmountWithCurrencySymbol(payerCost.getInstallmentAmount(), currency);

        final String x = installmentsTextView.getContext().getString(R.string.px_installments_by);

        installmentsTextView.setText(
            new SpannableStringBuilder(String.format(Locale.getDefault(), "%d", payerCost.getInstallments()))
                .append(x).append(" ")
                .append(spannedInstallmentsText));
    }

    /* default */ void highLight() {
        highlight.setVisibility(View.VISIBLE);
    }

    /* default */ void noHighLight() {
        highlight.setVisibility(View.GONE);
    }
}