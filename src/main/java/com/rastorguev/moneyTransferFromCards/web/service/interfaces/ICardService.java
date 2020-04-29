package com.rastorguev.moneyTransferFromCards.web.service.interfaces;

import com.rastorguev.moneyTransferFromCards.web.dto.CardDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.Card;
import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;

public interface ICardService {

    Iterable<CardDTO> findAllCardByOwnerIdEquals(Long ownerId);

    Iterable<Long> findAllCardNumberByOwnerIdEquals(Long ownerId);

    void addCard(long userId);

    CardDTO createCard(long userId);

    void deleteCard(long currentUserId, long number) throws NoSuchElement;

    long findOwnerIdByCardNumber(long cardNumber) throws NoSuchElement;

    Card findCardByCardNumber(long cardNumber);

    void makeTransaction(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney);

    void increaseAmountOfMoneyOnCard(long cardNumber, float money);

    CardDTO fromCard(Card card);
}

