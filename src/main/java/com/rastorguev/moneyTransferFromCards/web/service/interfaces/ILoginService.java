package com.rastorguev.moneyTransferFromCards.web.service.interfaces;

import com.rastorguev.moneyTransferFromCards.web.model.entity.User;
import com.rastorguev.moneyTransferFromCards.web.model.entity.UserPrivateData;

public interface ILoginService {

     User findUserByLoginAndPassword(String login, String password) ;

     User signUpNewUser(User user, UserPrivateData userPrivateData);

     boolean isUserWithSuchLoginExist(String login);
}
