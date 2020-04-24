package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.model.entity.User;
import com.rastorguev.moneyTransferFromCards.web.model.entity.UserPrivateData;
import com.rastorguev.moneyTransferFromCards.web.repository.UserPrivateDataRepository;
import com.rastorguev.moneyTransferFromCards.web.repository.UserRepository;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    final UserRepository userRepository;
    final UserPrivateDataRepository userPrivateDataRepository;

    public UserService(UserRepository userRepository, UserPrivateDataRepository userPrivateDataRepository) {
        this.userRepository = userRepository;
        this.userPrivateDataRepository = userPrivateDataRepository;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        long userID = (userPrivateDataRepository.findByLoginAndPassword(login, password)).getOwnerId();
        return userRepository.findById(userID).get();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User createUser(User user, UserPrivateData userPrivateData) {
        User newUser = userRepository.save(new User(user.getLastName(), user.getFirstName(), user.getMiddleName()));
        userPrivateData.setOwnerId(newUser.getId());
        userPrivateDataRepository.save(userPrivateData);
        return newUser;
    }

    @Override
    public boolean isUserWithSuchLoginExist(String login) {
        return userPrivateDataRepository.existsByLogin(login);
    }

}
