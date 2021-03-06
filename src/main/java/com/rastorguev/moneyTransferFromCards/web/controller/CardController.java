package com.rastorguev.moneyTransferFromCards.web.controller;

import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class CardController {

    final
    ICardService cardService;

    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping(value = "/list-cards", method = RequestMethod.GET)
    public String showCards(ModelMap model) {
        User user = (User) model.get("currentUser");
        model.put("cards", cardService.findAllCardByOwnerIdEquals(user.getId()));
        model.put("name", user.getFirstName());
        model.put("lastname", user.getLastName());
        model.put("errorMessage", model.get("errorMessage"));
        return "list-cards";
    }

    @RequestMapping(value = "/add-card", method = RequestMethod.GET)
    public String addCard(ModelMap model) {
        User user = (User) model.get("currentUser");
        cardService.addCard(user.getId());
        return "redirect:/list-cards";
    }

    @RequestMapping(value = "/delete-card", method = RequestMethod.GET)
    public String deleteCardByCardNumber(@RequestParam long number, ModelMap model) {
        try {
            cardService.
                    deleteCard(
                            ((User) model.get("currentUser")).getId(),
                            number);
        } catch (NoSuchElement noSuchElement) {
            model.put("errorMessage", noSuchElement.getMessage());
            noSuchElement.printStackTrace();
        }
        return "redirect:/list-cards";
    }
}
