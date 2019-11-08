package com.mercadopago.android.px.internal.features.review_and_confirm.components.items;

import com.mercadopago.android.px.internal.features.review_and_confirm.models.ItemModel;
import com.mercadopago.android.px.internal.features.review_and_confirm.models.ItemsModel;
import com.mercadopago.android.px.mocks.CurrencyStub;
import com.mercadopago.android.px.model.Currency;
import com.mercadopago.android.px.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsModelTest {

    private final static String ITEM_IMAGE_URL = "item_image_url";
    private final static String ITEM_TITLE_1 = "item_title_1";
    private final static String ITEM_TITLE_2 = "item_title_2";
    private final static String ITEM_DESCRIPTION = "item_description";
    private final static Currency ITEM_CURRENCY = CurrencyStub.MLA.get();
    private final static BigDecimal ITEM_UNIT_PRICE_1 = new BigDecimal(100);
    private final static BigDecimal ITEM_UNIT_PRICE_2 = new BigDecimal(200);

    @Mock
    private Item itemWithDescription;

    @Mock
    private Item itemWithoutDescription;

    @Test
    public void whenUniqueItemHasDescriptionThenCreateComponent() {
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);
        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        Assert.assertEquals(1, model.itemsModelList.size());
    }

    @Test
    public void whenUniqueItemDoesntHaveDescriptionThenDontCreateComponent() {
        when(itemWithoutDescription.getDescription()).thenReturn("");

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithoutDescription);
        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        Assert.assertEquals(0, model.itemsModelList.size());
    }

    @Test
    public void whenUniqueItemDoesntHaveDescriptionAndQuantityIsTwoThenCreateComponent() {
        when(itemWithoutDescription.getDescription()).thenReturn("");
        when(itemWithoutDescription.getQuantity()).thenReturn(2);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithoutDescription);
        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);

        Assert.assertEquals(1, model.itemsModelList.size());
    }

    @Test
    public void whenMultipleItemsWithOrWithoutDescrptionThenShowBoth() {
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);
        when(itemWithoutDescription.getDescription()).thenReturn("");

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);
        itemList.add(itemWithoutDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        Assert.assertEquals(2, model.itemsModelList.size());
    }

    @Test
    public void whenMultipleItemsThenCreateItemModelWithTitleAsTitle() {
        when(itemWithDescription.getTitle()).thenReturn(ITEM_TITLE_1);
        when(itemWithoutDescription.getTitle()).thenReturn(ITEM_TITLE_2);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);
        itemList.add(itemWithoutDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel firstItem = itemModelList.get(0);
        final ItemModel secondItem = itemModelList.get(1);

        Assert.assertEquals(2, model.itemsModelList.size());
        Assert.assertEquals(ITEM_TITLE_1, firstItem.title);
        Assert.assertEquals(ITEM_TITLE_2, secondItem.title);
    }

    @Test
    public void whenUniqueItemThenCreateItemModelWithDescriptionAsTitle() {
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel uniqueItem = itemModelList.get(0);

        Assert.assertEquals(1, model.itemsModelList.size());
        Assert.assertEquals(ITEM_DESCRIPTION, uniqueItem.title);
    }

    @Test
    public void createItemModelWithItemPictureUrl() {
        when(itemWithDescription.getPictureUrl()).thenReturn(ITEM_IMAGE_URL);
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel uniqueItem = itemModelList.get(0);

        Assert.assertEquals(1, model.itemsModelList.size());
        Assert.assertEquals(ITEM_IMAGE_URL, uniqueItem.imageUrl);
    }

    @Test
    public void whenMultipleItemsThenCreateItemModelWithDescriptionAsSubtitle() {
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);
        when(itemWithoutDescription.getDescription()).thenReturn("");

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);
        itemList.add(itemWithoutDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel firstItem = itemModelList.get(0);
        final ItemModel secondItem = itemModelList.get(1);

        Assert.assertEquals(2, model.itemsModelList.size());
        Assert.assertEquals(ITEM_DESCRIPTION, firstItem.subtitle);
        Assert.assertEquals("", secondItem.subtitle);
    }

    @Test
    public void whenUniqueItemThenCreateItemModelWithNoSubtitle() {
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel uniqueItem = itemModelList.get(0);

        Assert.assertEquals(1, model.itemsModelList.size());
        Assert.assertNull(uniqueItem.subtitle);
    }

    @Test
    public void createItemModelWithItemQuantity() {
        when(itemWithDescription.getQuantity()).thenReturn(1);
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel uniqueItem = itemModelList.get(0);

        Assert.assertEquals(1, model.itemsModelList.size());
        Assert.assertTrue(uniqueItem.quantity == 1);
    }

    @Test
    public void whenMultipleItemsThenCreateItemModelWithUnitPriceFromItem() {
        when(itemWithDescription.getUnitPrice()).thenReturn(ITEM_UNIT_PRICE_1);
        when(itemWithoutDescription.getUnitPrice()).thenReturn(ITEM_UNIT_PRICE_2);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);
        itemList.add(itemWithoutDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel firstItem = itemModelList.get(0);
        final ItemModel secondItem = itemModelList.get(1);

        Assert.assertEquals(2, model.itemsModelList.size());
        Assert.assertEquals(ITEM_UNIT_PRICE_1.toString(), firstItem.unitPrice);
        Assert.assertEquals(ITEM_UNIT_PRICE_2.toString(), secondItem.unitPrice);
    }

    @Test
    public void whenUniqueItemWithCardinalityThenCreateItemModelWithUnitPriceFromItem() {
        when(itemWithDescription.getUnitPrice()).thenReturn(ITEM_UNIT_PRICE_1);
        when(itemWithDescription.getQuantity()).thenReturn(2);
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);
        when(itemWithDescription.hasCardinality()).thenReturn(true);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel uniqueItem = itemModelList.get(0);

        Assert.assertEquals(1, model.itemsModelList.size());
        Assert.assertEquals(ITEM_UNIT_PRICE_1.toString(), uniqueItem.unitPrice);
    }

    @Test
    public void whenUniqueItemWithNoCardinalityThenCreateItemModelWithoutUnitPrice() {
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);
        when(itemWithDescription.hasCardinality()).thenReturn(false);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel uniqueItem = itemModelList.get(0);

        Assert.assertEquals(1, model.itemsModelList.size());
        Assert.assertEquals("", uniqueItem.unitPrice);
    }

    @Test
    public void createItemModelWithCurrencyId() {
        when(itemWithDescription.getDescription()).thenReturn(ITEM_DESCRIPTION);

        final List<Item> itemList = new ArrayList<>();
        itemList.add(itemWithDescription);

        final ItemsModel model = new ItemsModel(ITEM_CURRENCY, itemList);
        final List<ItemModel> itemModelList = model.itemsModelList;
        final ItemModel uniqueItem = itemModelList.get(0);

        Assert.assertEquals(1, model.itemsModelList.size());
        Assert.assertEquals(ITEM_CURRENCY, uniqueItem.currency);
    }
}
