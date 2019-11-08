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
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public final class PayerInformationIdentificationPage extends PageObject<CheckoutValidator> {

    public PayerInformationIdentificationPage(final CheckoutValidator validator) {
        super(validator);
    }

    public PayerInformationIdentificationPage enterIdentificationTypeAndNumber(@NonNull final String idType,
        @NonNull final String idNumber) {
        selectIdentificationType(idType);
        onView(withId(com.mercadopago.android.px.R.id.mpsdkCardIdentificationNumber)).perform(typeText(idNumber));
        return new PayerInformationIdentificationPage(validator);
    }

    public PayerInformationFirstNamePage pressNextButtonToFirstNamePage() {
        final Matcher<View> cardNextButtonTextMatcher = withId(com.mercadopago.android.px.R.id.mpsdkNextButton);
        onView(cardNextButtonTextMatcher).perform(click());
        return new PayerInformationFirstNamePage(validator);
    }

    public PayerInformationBusinessNamePage pressNextButtonToBusinesstNamePage() {
        final Matcher<View> cardNextButtonTextMatcher = withId(com.mercadopago.android.px.R.id.mpsdkNextButton);
        onView(cardNextButtonTextMatcher).perform(click());
        return new PayerInformationBusinessNamePage(validator);
    }

    private void selectIdentificationType(@NonNull final String idType) {
        onView(withId(com.mercadopago.android.px.R.id.mpsdkItemTitle)).perform(click());
        onView(withText(idType)).perform(click());
    }

    @Override
    public PayerInformationIdentificationPage validate(final CheckoutValidator validator) {
        validator.validate(this);
        return this;
    }

    public PaymentMethodPage pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
        return new PaymentMethodPage(validator);
    }
}