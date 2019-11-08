package com.mercadopago.android.px.internal.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.internal.repository.AmountRepository;
import com.mercadopago.android.px.internal.viewmodel.AmountLocalized;
import com.mercadopago.android.px.internal.viewmodel.ChargeLocalized;
import com.mercadopago.android.px.internal.viewmodel.DiscountAmountLocalized;
import com.mercadopago.android.px.internal.viewmodel.DiscountDescriptionLocalized;
import com.mercadopago.android.px.internal.viewmodel.DiscountDetailColor;
import com.mercadopago.android.px.internal.viewmodel.ItemLocalized;
import com.mercadopago.android.px.internal.viewmodel.SoldOutDiscountLocalized;
import com.mercadopago.android.px.internal.viewmodel.SummaryViewDefaultColor;
import com.mercadopago.android.px.internal.viewmodel.SummaryViewDetailDrawable;
import com.mercadopago.android.px.model.Currency;
import com.mercadopago.android.px.model.Discount;
import com.mercadopago.android.px.model.DiscountConfigurationModel;
import com.mercadopago.android.px.model.commission.PaymentTypeChargeRule;
import com.mercadopago.android.px.model.internal.SummaryInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SummaryDetailDescriptorFactory {

    @NonNull private final AmountDescriptorView.OnClickListener listener;
    @NonNull private final DiscountConfigurationModel discountModel;
    @NonNull private final AmountRepository amountRepository;
    @NonNull private final SummaryInfo summaryInfo;
    @NonNull private final Currency currency;
    @Nullable private final PaymentTypeChargeRule chargeRule;

    public SummaryDetailDescriptorFactory(@NonNull final AmountDescriptorView.OnClickListener listener,
        @NonNull final DiscountConfigurationModel discountModel, @NonNull final AmountRepository amountRepository,
        @NonNull final SummaryInfo summaryInfo, @NonNull final Currency currency,
        @Nullable final PaymentTypeChargeRule chargeRule) {
        this.listener = listener;
        this.discountModel = discountModel;
        this.amountRepository = amountRepository;
        this.summaryInfo = summaryInfo;
        this.currency = currency;
        this.chargeRule = chargeRule;
    }

    public List<AmountDescriptorView.Model> create() {
        final List<AmountDescriptorView.Model> list = new ArrayList<>();

        addDiscountRow(list);
        if (chargeRule != null) {
            addChargesRow(list);
        }
        addTotalRow(list);

        return list;
    }

    private void addDiscountRow(@NonNull final Collection<AmountDescriptorView.Model> list) {
        final Discount discount = discountModel.getDiscount();
        if (!discountModel.isAvailable()) {
            list.add(new AmountDescriptorView.Model(new SoldOutDiscountLocalized(), new SummaryViewDefaultColor())
                .setDetailDrawable(new SummaryViewDetailDrawable(), new SummaryViewDefaultColor())
                .setListener(v -> listener.onDiscountAmountDescriptorClicked(discountModel)));
        } else if (discount != null) {
            list.add(new AmountDescriptorView.Model(new DiscountDescriptionLocalized(discount),
                new DiscountAmountLocalized(discount.getCouponAmount(), currency), new DiscountDetailColor())
                .setDetailDrawable(new SummaryViewDetailDrawable(), new DiscountDetailColor())
                .setListener(v -> listener.onDiscountAmountDescriptorClicked(discountModel)));
        }
    }

    private void addChargesRow(@NonNull final Collection<AmountDescriptorView.Model> list) {
        final AmountDescriptorView.Model model = new AmountDescriptorView.Model(new ChargeLocalized(summaryInfo),
            new AmountLocalized(chargeRule.charge(), currency), new SummaryViewDefaultColor());
        if (chargeRule.hasDetailModal()) {
            model.setDetailDrawable(new SummaryViewDetailDrawable(), new SummaryViewDefaultColor())
                .setListener(v -> listener.onChargesAmountDescriptorClicked(chargeRule.getDetailModal()));
        }
        list.add(model);
    }

    private void addTotalRow(@NonNull final List<AmountDescriptorView.Model> list) {
        if (!list.isEmpty()) {
            list.add(0, new AmountDescriptorView.Model(new ItemLocalized(summaryInfo),
                new AmountLocalized(amountRepository.getItemsAmount(), currency), new SummaryViewDefaultColor()));
        }
    }
}