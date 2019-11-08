package com.mercadopago.android.px.internal.features.review_and_confirm.components.items;

import com.mercadopago.android.px.internal.features.review_and_confirm.models.ItemModel;
import com.mercadopago.android.px.mocks.CurrencyStub;
import com.mercadopago.android.px.model.Currency;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class ItemModelTest {

    private static final String ITEM_IMAGE_URL = "item_image_url";
    private static final String ITEM_TITLE = "item_title";
    private static final String ITEM_SUBTITLE = "item_subtitle";
    private static final Currency ITEM_CURRENCY = CurrencyStub.MLA.get();
    private static final BigDecimal ITEM_UNIT_PRICE = new BigDecimal(100);

    @Test
    public void whenQuantityIsUniqueThenHideIt() {
        final ItemModel model =
            new ItemModel(ITEM_IMAGE_URL, ITEM_TITLE, ITEM_SUBTITLE, 1, ITEM_CURRENCY, ITEM_UNIT_PRICE);
        Assert.assertFalse(model.hasToShowQuantity());
    }

    @Test
    public void whenQuantityIsMultipleThenShowIt() {
        final ItemModel model =
            new ItemModel(ITEM_IMAGE_URL, ITEM_TITLE, ITEM_SUBTITLE, 3, ITEM_CURRENCY, ITEM_UNIT_PRICE);
        Assert.assertTrue(model.hasToShowQuantity());
    }
}