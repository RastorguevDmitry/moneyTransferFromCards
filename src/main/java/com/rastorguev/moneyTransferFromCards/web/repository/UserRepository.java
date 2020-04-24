package com.rastorguev.moneyTransferFromCards.web.repository;

import com.rastorguev.moneyTransferFromCards.web.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}