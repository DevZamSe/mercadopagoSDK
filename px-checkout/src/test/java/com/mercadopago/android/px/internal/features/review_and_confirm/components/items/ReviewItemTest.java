package com.mercadopago.android.px.internal.features.review_and_confirm.components.items;

import com.mercadopago.android.px.internal.features.review_and_confirm.models.ItemModel;
import com.mercadopago.android.px.mocks.CurrencyStub;
import com.mercadopago.android.px.model.Currency;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class ReviewItemTest {

    private final static String ITEM_IMAGE_URL = "item_image_url";
    private final static String ITEM_TITLE = "item_title";
    private final static String ITEM_SUBTITLE = "item_subtitle";
    private final static Currency ITEM_CURRENCY = CurrencyStub.MLA.get();
    private final static BigDecimal ITEM_UNIT_PRICE = new BigDecimal(100);
    private final static Integer ITEM_ICON = 1;

    private ItemModel getItemModel(final String imageUrl, final String title, final String subtitle,
        final Integer quantity, final Currency currency, final BigDecimal unitPrice) {
        return new ItemModel(imageUrl, title, subtitle, quantity, currency, unitPrice);
    }

    @Test
    public void whenItemImageIsAvailableThenShowIt() {
        final ItemModel model =
            getItemModel(ITEM_IMAGE_URL, ITEM_TITLE, ITEM_SUBTITLE, 1, ITEM_CURRENCY, ITEM_UNIT_PRICE);
        final ReviewItem component = new ReviewItem(new ReviewItem.Props(model, ITEM_ICON, null, null));

        Assert.assertTrue(component.hasItemImage());
    }

    @Test
    public void whenItemImageIsNotAvailableThenShowIcon() {
        final ItemModel model = getItemModel(null, ITEM_TITLE, ITEM_SUBTITLE, 1, ITEM_CURRENCY, ITEM_UNIT_PRICE);
        final ReviewItem component = new ReviewItem(new ReviewItem.Props(model, ITEM_ICON, null, null));

        Assert.assertFalse(component.hasItemImage());
        Assert.assertTrue(component.hasIcon());
    }
}