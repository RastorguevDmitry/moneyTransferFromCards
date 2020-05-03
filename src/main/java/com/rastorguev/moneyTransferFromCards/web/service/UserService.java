package com.rastorguev.moneyTransferFromCards.web.service;

import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserRegisterDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.entity.UserPrivateData;
import com.rastorguev.moneyTransferFromCards.web.repository.IUserPrivateDataRepository;
import com.rastorguev.moneyTransferFromCards.web.repository.IUserRepository;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    final IUserRepository userRepository;
    final IUserPrivateDataRepository userPrivateDataRepository;

    public UserService(IUserRepository userRepository, IUserPrivateDataRepository userPrivateDataRepository) {
        this.userRepository = userRepository;
        this.userPrivateDataRepository = userPrivateDataRepository;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        UserPrivateData userPrivateData = (userPrivateDataRepository.findByLoginAndPassword(login, password));
        if (userPrivateData == null) {
            return null;
        }
        long userID = userPrivateData.getOwnerId();
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


    @Override
    public User createUserFromUserRegisterDTO(UserRegisterDTO userRegisterDTO) {
        User newUser = userRepository.save(new User(userRegisterDTO.getLastName(), userRegisterDTO.getFirstName(), userRegisterDTO.getMiddleName()));
        userPrivateDataRepository
                .save(
                        new UserPrivateData(
                                userRegisterDTO.getLogin(),
                                userRegisterDTO.getPassword(),
                                newUser.getId()));
        return newUser;
    }

    public UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getLastName(), user.getFirstName(), user.getMiddleName());
        return userDTO;
    }

}
