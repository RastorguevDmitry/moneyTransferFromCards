package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.model.entity.User;
import com.rastorguev.moneyTransferFromCards.web.model.entity.UserPrivateData;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ILoginService;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    final IUserService userService;

    public LoginService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        return userService.findUserByLoginAndPassword(login, password);
    }

    @Override
    public User signUpNewUser(User user, UserPrivateData userPrivateData) {
        return userService.createUser(user, userPrivateData);
    }

    @Override
    public boolean isUserWithSuchLoginExist(String login) {
        return userService.isUserWithSuchLoginExist(login);
    }

}
