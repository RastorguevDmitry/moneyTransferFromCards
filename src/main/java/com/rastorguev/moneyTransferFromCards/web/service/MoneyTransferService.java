package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.model.dto.MoneyTransfer;
import com.rastorguev.moneyTransferFromCards.web.model.dto.User;
import com.rastorguev.moneyTransferFromCards.web.repository.MoneyTransferRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
public class MoneyTransferService {

    final
    CardService cardService;

    final
    MoneyTransferRepository moneyTransferRepository;

    public MoneyTransferService(CardService cardService, MoneyTransferRepository moneyTransferRepository) {
        this.cardService = cardService;
        this.moneyTransferRepository = moneyTransferRepository;
    }

    public void makeTransaction(MoneyTransfer moneyTransfer) {
        cardService.makeTransaction(moneyTransfer.getOutgoingCardNumber(), moneyTransfer.getIncomingCardNumber(), moneyTransfer.getAmountOfMoney());
        if (moneyTransfer.getTimeToCompleteTransfer() == 0) {
            moneyTransfer.setTimeToCompleteTransfer(System.currentTimeMillis());
        }
        moneyTransferRepository.save(moneyTransfer);
    }

    public void makeIncomingTransactionWithThirdPartySource(long cardNumber, float money, long source) {
        MoneyTransfer moneyTransfer = new MoneyTransfer(source, cardNumber, money, System.currentTimeMillis());
        moneyTransferRepository.save(moneyTransfer);
        cardService.increaseAmountOfMoneyOnCard(cardNumber, money);
    }

    public Iterable<MoneyTransfer> findAllByOwnerAndAmountOfMoneyBetween(User user, float amountOfMoneyFrom, float amountOfMoneyTo) {

        Set<MoneyTransfer> result = new HashSet<>();

        Iterable<Long> cardsNumberFilteredByOwnerId = cardService.findAllCardNumberByOwnerIdEquals(user.getId());

        cardsNumberFilteredByOwnerId.forEach(number ->
                result.addAll((Collection<? extends MoneyTransfer>) moneyTransferRepository
                        .findAllByIncomingCardNumberAndAmountOfMoneyBetween(
                                number,
                                amountOfMoneyFrom,
                                amountOfMoneyTo))
        );
        cardsNumberFilteredByOwnerId.forEach(number ->
                result.addAll((Collection<? extends MoneyTransfer>) moneyTransferRepository
                        .findAllByOutgoingCardNumberAndAmountOfMoneyBetween(
                                number,
                                amountOfMoneyFrom,
                                amountOfMoneyTo))
        );

        return result;
    }

    public Iterable<MoneyTransfer> findAllByIncomingCardNumberIs(User user, long incomingCardNumber) {
        Iterable<Long> cardsNumberFilteredByOwnerId = cardService.findAllCardNumberByOwnerIdEquals(user.getId());

        return moneyTransferRepository
                .findAllByQueryOutgoingCardNumberInOrIncomingCardNumberInAndIncomingCardNumberIs(
                        cardsNumberFilteredByOwnerId,
                        incomingCardNumber);

    }

    public Iterable<MoneyTransfer> findAllOutgoingTransferByTimeToCompleteTransferBetween(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo) {
        Set<MoneyTransfer> result = new HashSet<>();
        Iterable<Long> cardsNumberFilteredByOwnerId = cardService.findAllCardNumberByOwnerIdEquals(user.getId());

        cardsNumberFilteredByOwnerId.forEach(number ->
                result.addAll((Collection<? extends MoneyTransfer>) moneyTransferRepository
                        .findAllByOutgoingCardNumberAndTimeToCompleteTransferBetween(
                                number,
                                timeToCompleteTransferFrom,
                                timeToCompleteTransferTo)));

        return result;
    }

    public float averageSpendingPerDayForPeriod(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo) {

        Iterable<MoneyTransfer> moneyTransfers = findAllOutgoingTransferByTimeToCompleteTransferBetween(
                user,
                timeToCompleteTransferFrom,
                timeToCompleteTransferTo);

        float sumPerPeriod = (float) StreamSupport.stream(
                (moneyTransfers)
                        .spliterator(), false)
                .mapToDouble(MoneyTransfer::getAmountOfMoney)
                .sum();

        int amountOfDay = (int) ((timeToCompleteTransferTo - timeToCompleteTransferFrom) / (24 * 60 * 60 * 1000));

        return sumPerPeriod / amountOfDay;
    }


    public Iterable<?> findSumOfTransfersByIncomingAccountsForPeriod(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo) {
        Iterable<?> result;
        Iterable<Long> cardsNumberFilteredByOwnerId = cardService.findAllCardNumberByOwnerIdEquals(user.getId());

        result = moneyTransferRepository
                .findSumOfTransfersByIncomingAccountsForPeriod(
                        cardsNumberFilteredByOwnerId,
                        timeToCompleteTransferFrom,
                        timeToCompleteTransferTo);
        return result;
    }


    //▪	Отношение поступлений к тратам
    public Float ratioIncomingToOutgoingTransfers(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo) {
        Iterable<Long> cardsNumberFilteredByOwnerId = cardService.findAllCardNumberByOwnerIdEquals(user.getId());

        return moneyTransferRepository.amountOfIncomingTransfers(
                cardsNumberFilteredByOwnerId,
                timeToCompleteTransferFrom,
                timeToCompleteTransferTo) /
                moneyTransferRepository.amountOfOutgoingTransfers(
                        cardsNumberFilteredByOwnerId,
                        timeToCompleteTransferFrom,
                        timeToCompleteTransferTo);
    }


    public Float operationWithMaximumAmountForPeriod(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo) {
        Iterable<Long> cardsNumberFilteredByOwnerId = cardService.findAllCardNumberByOwnerIdEquals(user.getId());

        return moneyTransferRepository.operationWithMaximumAmountForPeriod(
                cardsNumberFilteredByOwnerId,
                timeToCompleteTransferFrom,
                timeToCompleteTransferTo);
    }

}
