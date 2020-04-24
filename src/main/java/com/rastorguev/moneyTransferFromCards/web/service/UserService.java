package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.model.entity.User;
import com.rastorguev.moneyTransferFromCards.web.model.entity.UserPrivateData;
import com.rastorguev.moneyTransferFromCards.web.repository.UserPrivateDataRepository;
import com.rastorguev.moneyTransferFromCards.web.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    final UserRepository userRepository;
    final UserPrivateDataRepository userPrivateDataRepository;

    public UserService(UserRepository userRepository, UserPrivateDataRepository userPrivateDataRepository) {
        this.userRepository = userRepository;
        this.userPrivateDataRepository = userPrivateDataRepository;
    }


    public User retrieveUserByLoginAndPassword(String login, String password) {

        long userID = (userPrivateDataRepository.findByLoginAndPassword(login, password)).getOwnerId();

        return userRepository.findById(userID).get();
    }

    public User retrieveUserById(long id) {
        return userRepository.findById(id).get();
    }


    public User createNewUser(User user, UserPrivateData userPrivateData) {
        User newUser = userRepository.save(new User(user.getLastName(), user.getFirstName(), user.getMiddleName()));
        userPrivateData.setOwnerId(newUser.getId());
        userPrivateDataRepository.save(userPrivateData);
        return newUser;
    }

    public boolean checkExistenceUserWithSuchLogin(String login) {
        return userPrivateDataRepository.existsByLogin(login);
    }

}
