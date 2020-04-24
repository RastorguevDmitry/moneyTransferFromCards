package com.rastorguev.moneyTransferFromCards.web.service.interfaces;

import com.rastorguev.moneyTransferFromCards.web.model.entity.Card;

public interface ICardService {

    Iterable<Card> findAllCardByOwnerIdEquals(Long ownerId);

    Iterable<Long> findAllCardNumberByOwnerIdEquals(Long ownerId);

    void addCard(long userId);

    void deleteCard(long id);

    long findOwnerIdByCardNumber(long cardNumber);

    Card findCardByCardNumber(long cardNumber);

    void makeTransaction(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney);

    void increaseAmountOfMoneyOnCard(long cardNumber, float money);
}

