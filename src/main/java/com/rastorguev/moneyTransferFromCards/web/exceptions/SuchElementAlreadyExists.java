package com.rastorguev.moneyTransferFromCards.web.exceptions;

public class SuchElementAlreadyExists extends Exception {

    public SuchElementAlreadyExists(String message) {
        super(message);
    }

    public SuchElementAlreadyExists() {
        this("suchElementAlreadyExists");
    }
}