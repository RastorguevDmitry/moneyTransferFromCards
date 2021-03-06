package com.rastorguev.moneyTransferFromCards.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class CardDTO implements Serializable {

    private long number;
    private float amountOfMoneyOnCard;
    private long ownerId;


    public CardDTO(long number, float amountOfMoneyOnCard, long ownerId) {
        this.number = number;
        this.amountOfMoneyOnCard = amountOfMoneyOnCard;
        this.ownerId = ownerId;
    }

    public CardDTO(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public float getAmountOfMoneyOnCard() {
        return amountOfMoneyOnCard;
    }

    public void setAmountOfMoneyOnCard(float amountOfMoneyOnCard) {
        this.amountOfMoneyOnCard = amountOfMoneyOnCard;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return number == cardDTO.number &&
                Float.compare(cardDTO.amountOfMoneyOnCard, amountOfMoneyOnCard) == 0 &&
                ownerId == cardDTO.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, amountOfMoneyOnCard, ownerId);
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "number=" + number +
                ", amountOfMoneyOnCard=" + amountOfMoneyOnCard +
                ", ownerId=" + ownerId +
                '}';
    }
}
