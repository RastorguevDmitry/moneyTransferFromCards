package com.rastorguev.moneyTransferFromCards.web.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class MoneyTransfer implements Serializable {
    private static final long serialVersionUID = -677777501781283703L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long moneyTransferID;
    @Column
    private long outgoingCardNumber;
    @Column
    private long incomingCardNumber;
    @Column
    private float amountOfMoney;
    @Column
    private long timeToCompleteTransfer;

    public MoneyTransfer() {
    }

    public MoneyTransfer(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney, long currentTimeMillis) {
        this.outgoingCardNumber = outgoingCardNumber;
        this.incomingCardNumber = incomingCardNumber;
        this.amountOfMoney = amountOfMoney;
        this.timeToCompleteTransfer = currentTimeMillis;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyTransfer that = (MoneyTransfer) o;
        return moneyTransferID == that.moneyTransferID &&
                outgoingCardNumber == that.outgoingCardNumber &&
                incomingCardNumber == that.incomingCardNumber &&
                Float.compare(that.amountOfMoney, amountOfMoney) == 0 &&
                timeToCompleteTransfer == that.timeToCompleteTransfer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moneyTransferID, outgoingCardNumber, incomingCardNumber, amountOfMoney, timeToCompleteTransfer);
    }
}
