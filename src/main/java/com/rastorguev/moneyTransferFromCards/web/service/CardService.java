package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.model.entity.Card;
import com.rastorguev.moneyTransferFromCards.web.repository.ICardRepository;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CardService implements ICardService {

    private final ICardRepository cardRepository;

    public CardService(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Iterable<Card> findAllCardByOwnerIdEquals(Long ownerId) {
        return cardRepository.findAllCardByOwnerIdEquals(ownerId);
    }

    @Override
    public Iterable<Long> findAllCardNumberByOwnerIdEquals(Long ownerId) {

        Set<Long> cardsNumberFilteredByOwnerId = new HashSet<>();
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
    public void deleteCard(long currentUserId, long number) throws NoSuchElement {

        Set<Long> allCardNumberBelongCurrentUser = (HashSet<Long>) findAllCardNumberByOwnerIdEquals(currentUserId);
        if (allCardNumberBelongCurrentUser.contains(number)) {
            cardRepository.deleteById(number);
        } else {
            throw new NoSuchElement("you don't have card with number " + number);
        }
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
