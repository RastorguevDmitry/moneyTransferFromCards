package com.rastorguev.moneyTransferFromCards.web.repository;

import com.rastorguev.moneyTransferFromCards.web.model.dto.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    Iterable<Card> findAllCardByOwnerIdEquals(Long ownerId);
}
