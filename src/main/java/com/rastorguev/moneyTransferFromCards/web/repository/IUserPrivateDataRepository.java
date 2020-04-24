package com.rastorguev.moneyTransferFromCards.web.repository;

import com.rastorguev.moneyTransferFromCards.web.model.entity.UserPrivateData;
import org.springframework.data.repository.CrudRepository;

public interface IUserPrivateDataRepository extends CrudRepository<UserPrivateData, Long> {

    UserPrivateData findByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);
}