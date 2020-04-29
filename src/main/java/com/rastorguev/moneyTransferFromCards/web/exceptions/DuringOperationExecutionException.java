package com.rastorguev.moneyTransferFromCards.web.exceptions;

public class DuringOperationExecutionException extends Exception {

    public DuringOperationExecutionException(String message) {
        super(message);
    }

    public DuringOperationExecutionException() {
        this("что то пошло не так");
    }
}