package com.rastorguev.moneyTransferFromCards.web.service.interfaces;

import com.rastorguev.moneyTransferFromCards.web.dto.MoneyTransferDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.MoneyTransfer;
import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.exceptions.DuringOperationExecutionException;

public interface IMoneyTransferService {


    void makeTransaction(MoneyTransfer moneyTransfer) throws DuringOperationExecutionException;

    void makeIncomingTransactionWithThirdPartySource(long cardNumber, float money, long source);

    Iterable<MoneyTransfer> findAllByOwnerAndAmountOfMoneyBetween(User user, float amountOfMoneyFrom, float amountOfMoneyTo);

    Iterable<MoneyTransfer> findAllByIncomingCardNumberIs(User user, long incomingCardNumber);

    Iterable<MoneyTransfer> findAllOutgoingTransferByTimeToCompleteTransferBetween(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    float averageSpendingPerDayForPeriod(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    Iterable<?> findSumOfTransfersByIncomingAccountsForPeriod(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    Float ratioIncomingToOutgoingTransfers(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    Float operationWithMaximumAmountForPeriod(User user, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    MoneyTransferDTO makeTransactionWithDTO(MoneyTransferDTO moneyTransferDTO) throws DuringOperationExecutionException;

    MoneyTransfer fromMoneyTransferDTO(MoneyTransferDTO moneyTransferDTO);

    MoneyTransferDTO fromMoneyTransfer(MoneyTransfer moneyTransfer);
}
