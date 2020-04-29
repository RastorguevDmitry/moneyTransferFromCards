package com.rastorguev.moneyTransferFromCards.web.repository;

import com.rastorguev.moneyTransferFromCards.web.entity.MoneyTransfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMoneyTransferRepository extends CrudRepository<MoneyTransfer, Long> {




    Iterable<MoneyTransfer> findAllByIncomingCardNumberAndAmountOfMoneyBetween(
            long cardNumber, float amountOfMoneyFrom, float amountOfMoneyTo);

    Iterable<MoneyTransfer> findAllByOutgoingCardNumberAndAmountOfMoneyBetween(
            long cardNumber, float amountOfMoneyFrom, float amountOfMoneyTo);

    @Query(value = "select mt from MoneyTransfer mt where (mt.outgoingCardNumber in ?1 or mt.incomingCardNumber in ?1) and mt.incomingCardNumber = ?2")
    Iterable<MoneyTransfer> findAllByQueryOutgoingCardNumberInOrIncomingCardNumberInAndIncomingCardNumberIs(
            Iterable<Long> cardNumber, long searchedIncomingCardNumber);

    Iterable<MoneyTransfer> findAllByIncomingCardNumberAndTimeToCompleteTransferBetween(
            long incomingCardNumber, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    Iterable<MoneyTransfer> findAllByOutgoingCardNumberAndTimeToCompleteTransferBetween(
            long outgoingCardNumber, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    //▪	** Отображения статистики за выбранный период (при реализации с использованием СУБД):
    //▪	Операция с максимальной суммой за период / по всем картам  /входйщие и исходящие операции
    @Query(value = "select max(mt.amountOfMoney)  from MoneyTransfer mt " +
            "where (mt.outgoingCardNumber in ?1 or mt.incomingCardNumber in ?1) and (mt.timeToCompleteTransfer >= ?2 and mt.timeToCompleteTransfer <= ?3)")
    Float operationWithMaximumAmountForPeriod(
            Iterable<Long> cardNumber, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    //▪	Сумма переводов в разрезе счетов получателей за период
    @Query(value = "select sum(mt.amountOfMoney), mt.incomingCardNumber  from MoneyTransfer mt " +
            "where mt.outgoingCardNumber in ?1 and (mt.timeToCompleteTransfer >= ?2 and mt.timeToCompleteTransfer <= ?3)" +
            "GROUP BY mt.incomingCardNumber")
    Iterable<List<Float>> findSumOfTransfersByIncomingAccountsForPeriod(
            Iterable<Long> cardNumber, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    //▪	Отношение поступлений к тратам / поступления
    @Query(value = "select sum(mt.amountOfMoney)  from MoneyTransfer mt " +
            "where mt.incomingCardNumber in ?1 and (mt.timeToCompleteTransfer >= ?2 and mt.timeToCompleteTransfer <= ?3)")
    Float amountOfIncomingTransfers(
            Iterable<Long> cardNumber, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

    //▪	Отношение поступлений к тратам / траты
    @Query(value = "select sum(mt.amountOfMoney)  from MoneyTransfer mt " +
            "where mt.outgoingCardNumber in ?1 and (mt.timeToCompleteTransfer >= ?2 and mt.timeToCompleteTransfer <= ?3)")
    Float amountOfOutgoingTransfers(
            Iterable<Long> cardNumber, long timeToCompleteTransferFrom, long timeToCompleteTransferTo);

}
