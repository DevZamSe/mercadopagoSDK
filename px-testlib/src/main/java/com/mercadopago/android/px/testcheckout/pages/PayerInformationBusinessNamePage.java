package com.mercadopago.android.px.testcheckout.pages;

import android.support.annotation.NonNull;
import android.support.test.espresso.action.ViewActions;
import android.view.View;
import com.mercadopago.android.px.testcheckout.assertions.CheckoutValidator;
import com.mercadopago.android.testlib.pages.PageObject;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public final class PayerInformationBusinessNamePage extends PageObject<CheckoutValidator> {

    public PayerInformationBusinessNamePage(final CheckoutValidator validator) {
        super(validator);
    }

    public PayerInformationBusinessNamePage enterBusinessName(@NonNull final String businessName) {
        onView(withId(com.mercadopago.android.px.R.id.mpsdkBusinessName)).perform(typeText(businessName));
        return new PayerInformationBusinessNamePage(validator);
    }

    public ReviewAndConfirmPage pressNextButton() {
        final Matcher<View> cardNextButtonTextMatcher = withId(com.mercadopago.android.px.R.id.mpsdkNextButton);
        onView(cardNextButtonTextMatcher).perform(click());
        return new ReviewAndConfirmPage(validator);
    }

    @Override
    public PayerInformationBusinessNamePage validate(final CheckoutValidator validator) {
        validator.validate(this);
        return this;
    }

    public PaymentMethodPage pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
        return new PaymentMethodPage(validator);
    }
}