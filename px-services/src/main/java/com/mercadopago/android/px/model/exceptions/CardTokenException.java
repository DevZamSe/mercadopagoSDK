package com.mercadopago.android.px.model.exceptions;

public class CardTokenException extends Exception {

    public static final int INVALID_EMPTY_CARD = 1;
    public static final int INVALID_CARD_BIN = 2;
    public static final int INVALID_CARD_LENGTH = 3;
    public static final int INVALID_CARD_LUHN = 4;
    public static final int INVALID_CVV_LENGTH = 5;
    public static final int INVALID_FIELD = 6;
    public static final int INVALID_CARD_NUMBER_INCOMPLETE = 7;
    public static final int INVALID_PAYMENT_METHOD = 8;

    private final int errorCode;
    private String extraParams;

    public CardTokenException(final int errorCode) {
        this.errorCode = errorCode;
    }

    public CardTokenException(final int errorCode, final String extraParams) {
        this.errorCode = errorCode;
        this.extraParams = extraParams;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getExtraParams() {
        return extraParams;
    }
}
