package com.rastorguev.moneyTransferFromCards.web.repository;

import com.rastorguev.moneyTransferFromCards.web.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
}