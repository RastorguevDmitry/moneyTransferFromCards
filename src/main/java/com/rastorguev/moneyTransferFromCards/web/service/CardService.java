package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.dto.CardDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.Card;
import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.repository.ICardRepository;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CardService implements ICardService {

    private final ICardRepository cardRepository;

    public CardService(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Iterable<CardDTO> findAllCardByOwnerIdEquals(Long ownerId) {

        List<CardDTO> cardDTOs = new ArrayList<>();
        cardRepository
                .findAllCardByOwnerIdEquals(ownerId)
                .forEach(card -> cardDTOs.add(fromCard(card)));
        return cardDTOs;
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
    public CardDTO createCard(long userId) {
       return fromCard(cardRepository.save(new Card(userId)));
    }

    @Override
    public void deleteCard(long currentUserId, long number) throws NoSuchElement {
        if (cardRepository.existsByNumberAndOwnerId(number, currentUserId)) {
            cardRepository.deleteById(number);
        } else {
            throw new NoSuchElement("you don't have card with number " + number);
        }
    }

    @Override
    public long findOwnerIdByCardNumber(long cardNumber) throws NoSuchElement {
        Card card = cardRepository.findById(cardNumber).get();
        if (card != null) {
            return card.getOwnerId();
        } else {
            throw new NoSuchElement("такой карты не существует");
        }
    }

    @Override
    public Card findCardByCardNumber(long cardNumber) {
        return cardRepository.findById(cardNumber).get();
    }

    @Transactional
    @Override
    public void makeTransaction(long outgoingCardNumber, long incomingCardNumber, float amountOfMoney) {
        Card outgoingCard = findCardByCardNumber(outgoingCardNumber);
        Card incomingCard = findCardByCardNumber(incomingCardNumber);

        float outgoingCardAmountOfMoneyBeforeTransaction = outgoingCard.getAmountOfMoneyOnCard();
        float incomingCardAmountOfMoneyBeforeTransaction  = incomingCard.getAmountOfMoneyOnCard();

        outgoingCard.reduceAmountOfMoneyOnCard(amountOfMoney);
        incomingCard.increaseAmountOfMoneyOnCard(amountOfMoney);

        cardRepository.compareAndSave(outgoingCard.getNumber(), outgoingCard.getAmountOfMoneyOnCard(), outgoingCardAmountOfMoneyBeforeTransaction);
        cardRepository.compareAndSave(incomingCard.getNumber() ,incomingCard.getAmountOfMoneyOnCard(), incomingCardAmountOfMoneyBeforeTransaction);
    }

    @Override
    public void increaseAmountOfMoneyOnCard(long cardNumber, float money) {
        Card card = findCardByCardNumber(cardNumber);
        card.increaseAmountOfMoneyOnCard(money);
        cardRepository.save(card);
    }

    @Override
    public CardDTO fromCard(Card card) {
        CardDTO cardDTO = new CardDTO(card.getNumber(), card.getAmountOfMoneyOnCard(), card.getOwnerId());
        return cardDTO;
    }


}
