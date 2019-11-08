package com.mercadopago.android.testlib.matchers;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class CustomViewMatchers {

    private CustomViewMatchers() {
    }

    public static Matcher<View> withValueEqualToTextView(final int viewId) {
        return CustomTypeSafeMatcher.create(viewId);
    }

    public static Matcher<View> withDigitsOnlyEqualsToTextView(final int viewId) {
        return CustomTypeSafeMatcher.createForDigits(viewId);
    }

    static final class CustomTypeSafeMatcher extends TypeSafeMatcher<View> {

        private static final String EXTRACT_DIGITS_REGEX = "\\D+";
        private final boolean shouldExtractDigits;

        @IdRes private final int viewId;

        /* default */ static CustomTypeSafeMatcher create(@IdRes final int viewId) {
            return new CustomTypeSafeMatcher(viewId, false);
        }

        /* default */ static CustomTypeSafeMatcher createForDigits(@IdRes final int viewId) {
            return new CustomTypeSafeMatcher(viewId, true);
        }

        private CustomTypeSafeMatcher(@IdRes final int viewId, final boolean shouldExtractDigits) {
            this.viewId = viewId;
            this.shouldExtractDigits = shouldExtractDigits;
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("Has EditText/TextView the value equals to view:  " + viewId);
        }

        @Override
        public boolean matchesSafely(final View view) {

            final View viewById = view.getRootView().findViewById(viewId);
            if (!(view instanceof TextView || !(viewById instanceof TextView))) {
                return false;
            }

            //noinspection ConstantConditions
            final String targetText = ((TextView) viewById).getText().toString();
            //noinspection ConstantConditions
            String text = ((TextView) view).getText().toString();

            text = shouldExtractDigits ? text.replaceAll(EXTRACT_DIGITS_REGEX, "") : text;

            return text.equalsIgnoreCase(targetText);
        }
    }
}