package com.mercadopago.android.px.internal.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import com.mercadopago.android.px.internal.features.TermsAndConditionsActivity;
import com.mercadopago.android.px.model.display_info.LinkablePhrase;
import com.mercadopago.android.px.model.display_info.LinkableText;

public class LinkableTextView extends android.support.v7.widget.AppCompatTextView {

    private LinkableText model;

    public LinkableTextView(@NonNull final Context context, @NonNull final AttributeSet attrs) {
        super(context, attrs);
    }

    public void updateModel(@Nullable final LinkableText model) {
        if (model != null) {
            this.model = model;
            render();
        }
    }

    private void render() {
        if (!model.getText().isEmpty()) {
            final Spannable spannableText = new SpannableStringBuilder(model.getText());
            if (model.getLinkablePhrases() != null) {
                for (final LinkablePhrase linkablePhrase : model.getLinkablePhrases()) {
                    final int start = model.getText().indexOf(linkablePhrase.getPhrase());
                    final int end = start + linkablePhrase.getPhrase().length();
                    spannableText.setSpan(
                        new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull final View widget) {
                                onLinkClicked(linkablePhrase);
                            }
                        },
                        start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableText
                        .setSpan(new ForegroundColorSpan(Color.parseColor(linkablePhrase.getTextColor())), start, end,
                            0);
                }
            }

            setTextColor(Color.parseColor(model.getTextColor()));
            setText(spannableText);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    /* default */ void onLinkClicked(@NonNull final LinkablePhrase linkablePhrase) {
        final String data = linkablePhrase.getLink() != null ? linkablePhrase.getLink() : linkablePhrase.getHtml();
        TermsAndConditionsActivity.start(getContext(), data);
    }
}