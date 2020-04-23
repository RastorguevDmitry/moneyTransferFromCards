package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.model.dto.Card;
import com.rastorguev.moneyTransferFromCards.web.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Iterable<Card> findAllCardByOwnerIdEquals(Long ownerId) {
        return cardRepository.findAllCardByOwnerIdEquals(ownerId);
    }

    public Iterable<Long> findAllCardNumberByOwnerIdEquals(Long ownerId) {

        List<Long> cardsNumberFilteredByOwnerId = new ArrayList<>();
        findAllCardByOwnerIdEquals(ownerId)
                .forEach(
                        card -> cardsNumberFilteredByOwnerId.add(card.getNumber()
                        ));

        return cardsNumberFilteredByOwnerId;
    }

    public void addCard(long userId) {
        cardRepository.save(new Card(userId));
    }

    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }

    public long retrieveOwnerIdByCardNumber(long cardNumber) {
        return cardRepository.findById(cardNumber).get().getOwnerId();
    }


    public Card retrieveCardByCardNumber(long cardNumber) {
        return cardRepository.findById(cardNumber).get();
    }

    public void makeTransaction(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney) {
        Card outgoingCard = retrieveCardByCardNumber(outgoingCardNumber);
        Card incomingCard = retrieveCardByCardNumber(incomingCardNumber);

        outgoingCard.reduceAmountOfMoneyOnCard(amountOfMoney);
        incomingCard.increaseAmountOfMoneyOnCard(amountOfMoney);

        cardRepository.save(outgoingCard);
        cardRepository.save(incomingCard);
    }


    public void increaseAmountOfMoneyOnCard(long cardNumber, float money) {
        Card card = retrieveCardByCardNumber(cardNumber);
        card.increaseAmountOfMoneyOnCard(money);
        cardRepository.save(card);
    }
}
