package com.rastorguev.moneyTransferFromCards.web.service.interfaces;

import com.rastorguev.moneyTransferFromCards.web.model.entity.User;
import com.rastorguev.moneyTransferFromCards.web.model.entity.UserPrivateData;

public interface IUserService {

     User findUserByLoginAndPassword(String login, String password);

     User findUserById(long id);

     User createUser(User user, UserPrivateData userPrivateData);

     boolean isUserWithSuchLoginExist(String login);
}

