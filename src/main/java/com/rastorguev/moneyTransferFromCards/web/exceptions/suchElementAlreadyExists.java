package com.rastorguev.moneyTransferFromCards.web.exceptions;

public class suchElementAlreadyExists extends Exception {

    public suchElementAlreadyExists(String message) {
        super(message);
    }

    public suchElementAlreadyExists() {
        this("suchElementAlreadyExists");
    }
}