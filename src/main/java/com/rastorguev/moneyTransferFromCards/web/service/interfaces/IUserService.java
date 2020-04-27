package com.rastorguev.moneyTransferFromCards.web.service.interfaces;

import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.entity.UserPrivateData;

public interface IUserService {

     User findUserByLoginAndPassword(String login, String password);

     User findUserById(long id);

     User createUser(User user, UserPrivateData userPrivateData);

     boolean isUserWithSuchLoginExist(String login);
}

