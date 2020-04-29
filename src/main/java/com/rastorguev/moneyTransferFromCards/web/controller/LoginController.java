package com.rastorguev.moneyTransferFromCards.web.controller;

import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.entity.UserPrivateData;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

    final
    IUserService userService;

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String validateUserAndOpenListCard(ModelMap model, @RequestParam String login, @RequestParam String password) {
        User user = userService.findUserByLoginAndPassword(login, password);
        if (user == null) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
        model.put("currentUser", user);
        return "redirect:/list-cards";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String showSignUnPage(ModelMap model) {
        model.put("currentUser", new User());
        model.put("userPrivateData", new UserPrivateData());
        return "sign-up";
    }


    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUnPage(ModelMap model, User currentUser, UserPrivateData userPrivateData) {
        if (userService.isUserWithSuchLoginExist(userPrivateData.getLogin())) {
            model.put("errorMessage", "user with such login already exist");
            return "sign-up";
        } else {
            currentUser = userService.createUser(currentUser, userPrivateData);
            model.put("currentUser", currentUser);
            return "redirect:/list-cards";
        }
    }
}
