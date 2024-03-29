package com.mercadopago.android.px.internal.features.review_and_confirm.components.payer_information;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mercadolibre.android.ui.widgets.MeliButton;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.util.MPCardMaskUtil;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.internal.util.ViewUtils;
import com.mercadopago.android.px.internal.view.CompactComponent;
import com.mercadopago.android.px.internal.view.MPTextView;
import com.mercadopago.android.px.model.IdentificationType;
import com.mercadopago.android.px.model.Payer;

public class PayerInformationComponent extends CompactComponent<Payer, PayerInformationComponent.Actions> {

    public interface Actions {
        void onModifyPayerInformationClicked();
    }

    public PayerInformationComponent(@NonNull final Payer props, @NonNull final Actions actions) {
        super(props, actions);
    }

    @Override
    public View render(@NonNull final ViewGroup parent) {
        final Context context = parent.getContext();
        final ViewGroup payerInfoView = (ViewGroup) ViewUtils.inflate(parent, R.layout.px_payer_information);
        final MPTextView docTypeAndNumber = payerInfoView.findViewById(R.id.payer_doc_type_and_number);
        final MPTextView payerAppellation = payerInfoView.findViewById(R.id.payer_appellation);
        final ImageView icon = payerInfoView.findViewById(R.id.icon);

        ViewUtils.loadOrGone(getIdentificationTypeAndNumber(context), docTypeAndNumber);
        ViewUtils.loadOrGone(getPayerAppellation(context), payerAppellation);
        drawIconFromRes(icon, R.drawable.px_payer_information);
        drawModifyButton(payerInfoView);

        return payerInfoView;
    }

    private void drawModifyButton(@NonNull final ViewGroup payerInfoView) {
        final MeliButton buttonLink = payerInfoView.findViewById(R.id.payer_information_modify_button);
        buttonLink.setOnClickListener(v -> {
            if (getActions() != null) {
                getActions().onModifyPayerInformationClicked();
            }
        });
    }

    @NonNull
    private String getPayerAppellation(@NonNull final Context context) {
        //Business name is first name in v1/payments
        if (TextUtil.isEmpty(props.getLastName())) {
            return props.getFirstName().toUpperCase();
        } else {
            return context
                .getString(R.string.px_payer_information_first_and_last_name, props.getFirstName(), props.getLastName())
                .toUpperCase();
        }
    }

    @NonNull
    private String getIdentificationTypeAndNumber(@NonNull final Context context) {
        final int res = R.string.px_payer_information_identification_type_and_number;
        final IdentificationType identificationType = new IdentificationType();
        identificationType.setId(props.getIdentification().getType());

        final String identificationNumber = props.getIdentification().getNumber();
        try {
            identificationType.setMaxLength(identificationNumber.length());
        } catch (NumberFormatException e) {
            identificationType.setMaxLength(0);
        }
        final String maskedNumber =
            MPCardMaskUtil.buildIdentificationNumberWithMask(identificationNumber, identificationType);
        return context.getString(res, identificationType.getId(), maskedNumber);
    }

    private void drawIconFromRes(@NonNull final ImageView imageView, @DrawableRes final int resource) {
        imageView.setImageResource(resource);
    }
}