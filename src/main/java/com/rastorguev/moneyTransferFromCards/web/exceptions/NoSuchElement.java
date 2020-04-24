package com.rastorguev.moneyTransferFromCards.web.exceptions;

public class NoSuchElement extends Exception {

    public NoSuchElement(String message) {
        super(message);
    }

    public NoSuchElement() {
        this("Wrong number of element");
    }
}
