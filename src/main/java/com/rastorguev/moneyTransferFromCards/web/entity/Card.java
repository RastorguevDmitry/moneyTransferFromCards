package com.rastorguev.moneyTransferFromCards.web.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Card implements Serializable {

    private static final long serialVersionUID = -6453559436168273059L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;
    @Column
    private float amountOfMoneyOnCard;
    @Column(nullable = false)
    private long ownerId;


    public Card() {
    }

    public Card(long number, float amountOfMoneyOnCard, long ownerId) {
        this.number = number;
        this.amountOfMoneyOnCard = amountOfMoneyOnCard;
        this.ownerId = ownerId;
    }

    public Card(long ownerId) {
        this.ownerId = ownerId;
    }

    public void reduceAmountOfMoneyOnCard(float amountOfMoneyForReduce) {
        this.amountOfMoneyOnCard -= amountOfMoneyForReduce;
    }

    public void increaseAmountOfMoneyOnCard(float amountOfMoneyForIncrease) {
        this.amountOfMoneyOnCard += amountOfMoneyForIncrease;
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
}
