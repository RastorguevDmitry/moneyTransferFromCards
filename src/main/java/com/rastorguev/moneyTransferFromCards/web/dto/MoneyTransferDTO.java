package com.rastorguev.moneyTransferFromCards.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class MoneyTransferDTO implements Serializable {
    private long moneyTransferID;
    private long outgoingCardNumber;
    private long incomingCardNumber;
    private float amountOfMoney;
    private long timeToCompleteTransfer;

    private boolean confirmed;



    public MoneyTransferDTO(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney, long currentTimeMillis) {
        this.outgoingCardNumber = outgoingCardNumber;
        this.incomingCardNumber = incomingCardNumber;
        this.amountOfMoney = amountOfMoney;
        this.timeToCompleteTransfer = currentTimeMillis;
    }

    public MoneyTransferDTO(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney) {
        this.outgoingCardNumber = outgoingCardNumber;
        this.incomingCardNumber = incomingCardNumber;
        this.amountOfMoney = amountOfMoney;
    }

    public long getOutgoingCardNumber() {
        return outgoingCardNumber;
    }

    public void setOutgoingCardNumber(long outgoingCardNumber) {
        this.outgoingCardNumber = outgoingCardNumber;
    }

    public long getIncomingCardNumber() {
        return incomingCardNumber;
    }

    public void setIncomingCardNumber(long incomingCardNumber) {
        this.incomingCardNumber = incomingCardNumber;
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public long getTimeToCompleteTransfer() {
        return timeToCompleteTransfer;
    }

    public void setTimeToCompleteTransfer(long timeToCompleteTransfer) {
        this.timeToCompleteTransfer = timeToCompleteTransfer;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyTransferDTO that = (MoneyTransferDTO) o;
        return moneyTransferID == that.moneyTransferID &&
                outgoingCardNumber == that.outgoingCardNumber &&
                incomingCardNumber == that.incomingCardNumber &&
                Float.compare(that.amountOfMoney, amountOfMoney) == 0 &&
                timeToCompleteTransfer == that.timeToCompleteTransfer &&
                confirmed == that.confirmed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moneyTransferID, outgoingCardNumber, incomingCardNumber, amountOfMoney, timeToCompleteTransfer, confirmed);
    }
}
