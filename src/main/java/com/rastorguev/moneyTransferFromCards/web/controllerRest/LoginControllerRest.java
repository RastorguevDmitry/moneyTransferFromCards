package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserPrivateDataDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserRegisterDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.exceptions.SuchElementAlreadyExists;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/rest")
public class LoginControllerRest {

    final
    IUserService userService;

    public LoginControllerRest(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO validateUserAndOpenListCard(@RequestBody UserPrivateDataDTO userPrivateDataDTO) throws NoSuchElement {
        User user = userService
                .findUserByLoginAndPassword(
                        userPrivateDataDTO.getLogin(),
                        userPrivateDataDTO.getPassword()
                );
        if (user == null) {
            throw new NoSuchElement("Invalid Credentials");
        } else
            return userService.fromUser(user);
    }

    @PostMapping(value = "/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signUpUser(@RequestBody UserRegisterDTO userRegisterDTO) throws SuchElementAlreadyExists {
        if (userService
                .isUserWithSuchLoginExist(
                        userRegisterDTO.getLogin())) {
            throw new SuchElementAlreadyExists("user with such login AlreadyExists");
        } else {
            return userService
                    .fromUser(
                            userService
                                    .createUserFromUserRegisterDTO(
                                            userRegisterDTO)
                    );
        }
    }
}
