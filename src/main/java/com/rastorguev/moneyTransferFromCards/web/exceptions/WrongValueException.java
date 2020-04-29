package com.rastorguev.moneyTransferFromCards.web.exceptions;

public class WrongValueException extends Exception {

    public WrongValueException(String message) {
        super(message);
    }

    public WrongValueException() {
        this("wrong Value ");
    }
}