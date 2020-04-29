package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserPrivateDataDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserRegisterDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.exceptions.SuchElementAlreadyExists;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class LoginControllerRest {

    final
    IUserService userService;

    public LoginControllerRest(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> validateUser(@RequestBody UserPrivateDataDTO userPrivateDataDTO) throws NoSuchElement {
        User user = userService
                .findUserByLoginAndPassword(
                        userPrivateDataDTO.getLogin(),
                        userPrivateDataDTO.getPassword()
                );
        if (user == null) {
            throw new NoSuchElement("Invalid Credentials");
        } else
            return new ResponseEntity<>(userService.fromUser(user), HttpStatus.OK);

    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<UserDTO> signUpUser(@RequestBody UserRegisterDTO userRegisterDTO) throws SuchElementAlreadyExists {
        if (userService
                .isUserWithSuchLoginExist(
                        userRegisterDTO.getLogin())) {
            throw new SuchElementAlreadyExists("user with such login AlreadyExists");
        } else {

            return new ResponseEntity<>(userService
                    .fromUser(
                            userService
                                    .createUserFromUserRegisterDTO(
                                            userRegisterDTO)
                    ), HttpStatus.CREATED);
        }
    }
}
