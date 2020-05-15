package com.galaxy.merchant.enums;

public enum Error {
    SUCCESS_OK(0, "OK"), NO_INPUT(1, "No input was specified"),
    INVALID(2, "Input format is wrong "),
    INVALID_ROMAN_CHARACTER(3, "Illegal character specified in roman number"),
    INVALID_ROMAN_STRING(4, "wrong Roman number, voilated roman number format"),
    INCORRECT_LINE_TYPE(5, "Exception caused during processing due to incorrect line type supplied"),
    UNKNOWN(6, "I have no idea what you are talking about");

    private int errorCode;
    private String errorMessage;

    Error(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void printMessage() {
        System.out.println(getErrorMessage());
    }

    public static String getErrorMessage(int errorCode) {
        for (Error error : Error.values()) {
            if (error.getErrorCode() == errorCode) {
                return error.getErrorMessage();
            }
        }
        return "";
    }
}
