package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.model.entity.User;
import com.rastorguev.moneyTransferFromCards.web.model.entity.UserPrivateData;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public User findUserByLoginAndPassword(String login, String password) {
        return userService.retrieveUserByLoginAndPassword(login, password);
    }


    public User signUpNewUser(User user, UserPrivateData userPrivateData) {
        return userService.createNewUser(user, userPrivateData);
    }

    public boolean isUserWithSuchLoginExist(String login) {
        return userService.checkExistenceUserWithSuchLogin(login);
    }

}
