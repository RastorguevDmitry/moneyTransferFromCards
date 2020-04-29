package com.rastorguev.moneyTransferFromCards.web.repository;

import com.rastorguev.moneyTransferFromCards.web.entity.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends CrudRepository<Card, Long> {

    Iterable<Card> findAllCardByOwnerIdEquals(Long ownerId);

    boolean existsByNumberAndOwnerId(long numberOfCard, long userId);

    // перевод денег с карту на карту
    @Modifying
    @Query(value = "UPDATE Card SET amountOfMoneyOnCard = ?2 WHERE number=?1 and amountOfMoneyOnCard=?3")
    void compareAndSave(long cardNumber, float amountOfMoneyAfterTransaction, float amountOfMoneyBeforeTransaction);

}
