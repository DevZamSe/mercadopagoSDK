package com.mercadopago.android.px.testcheckout.pages;

import com.mercadopago.android.px.model.PaymentTypes;
import com.mercadopago.android.px.testcheckout.assertions.CheckoutValidator;
import com.mercadopago.android.testlib.pages.PageObject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;

public class CardPage extends PageObject<CheckoutValidator> {

    public CardPage() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    public CardPage(final CheckoutValidator validator) {
        super(validator);
    }

    public CreditCardPage selectCreditCard() {
        onView(withTagValue(is(PaymentTypes.CREDIT_CARD))).perform(click());
        return new CreditCardPage(validator);
    }

    public DebitCardPage selectDebitCard() {
        onView(withTagValue(is(PaymentTypes.DEBIT_CARD))).perform(click());
        return new DebitCardPage(validator);
    }

    @Override
    public CardPage validate(final CheckoutValidator validator) {
        validator.validate(this);
        return this;
    }
}