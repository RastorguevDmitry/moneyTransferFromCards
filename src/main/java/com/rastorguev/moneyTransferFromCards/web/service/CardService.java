package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.model.entity.Card;
import com.rastorguev.moneyTransferFromCards.web.repository.CardRepository;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService  implements ICardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Iterable<Card> findAllCardByOwnerIdEquals(Long ownerId) {
        return cardRepository.findAllCardByOwnerIdEquals(ownerId);
    }

    @Override
    public Iterable<Long> findAllCardNumberByOwnerIdEquals(Long ownerId) {

        List<Long> cardsNumberFilteredByOwnerId = new ArrayList<>();
        findAllCardByOwnerIdEquals(ownerId)
                .forEach(
                        card -> cardsNumberFilteredByOwnerId.add(card.getNumber()
                        ));

        return cardsNumberFilteredByOwnerId;
    }

    @Override
    public void addCard(long userId) {
        cardRepository.save(new Card(userId));
    }

    @Override
    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public long findOwnerIdByCardNumber(long cardNumber) {
        return cardRepository.findById(cardNumber).get().getOwnerId();
    }

    @Override
    public Card findCardByCardNumber(long cardNumber) {
        return cardRepository.findById(cardNumber).get();
    }

    @Override
    public void makeTransaction(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney) {
        Card outgoingCard = findCardByCardNumber(outgoingCardNumber);
        Card incomingCard = findCardByCardNumber(incomingCardNumber);

        outgoingCard.reduceAmountOfMoneyOnCard(amountOfMoney);
        incomingCard.increaseAmountOfMoneyOnCard(amountOfMoney);

        cardRepository.save(outgoingCard);
        cardRepository.save(incomingCard);
    }

    @Override
    public void increaseAmountOfMoneyOnCard(long cardNumber, float money) {
        Card card = findCardByCardNumber(cardNumber);
        card.increaseAmountOfMoneyOnCard(money);
        cardRepository.save(card);
    }
}
