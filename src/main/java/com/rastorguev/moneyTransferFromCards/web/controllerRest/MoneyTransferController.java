package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IMoneyTransferService;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class MoneyTransferController {


    final
    ICardService cardService;
    final
    IUserService userService;
    final
    IMoneyTransferService moneyTransferService;

    public MoneyTransferController(ICardService cardService, IUserService userService, IMoneyTransferService moneyTransferService) {
        this.cardService = cardService;
        this.userService = userService;
        this.moneyTransferService = moneyTransferService;
    }

//    @RequestMapping(value = "/topup-card-balance", method = RequestMethod.GET)
//    public String topUpBalanceCardByNumber(@RequestParam long number) {
//        moneyTransferService.makeIncomingTransactionWithThirdPartySource(number, 500, THIRD_PARTY_SOURS_FOU_TRANSACTION);
//        return "redirect:/list-cards";
//    }
//
//    @RequestMapping(value = "/transfer-card", method = RequestMethod.GET)
//    public String openTransferMoneyFromCardPage(@RequestParam long number, ModelMap model) {
//
//        Card card = cardService.findCardByCardNumber(number);
//
//        MoneyTransfer moneyTransfer = new MoneyTransfer();
//        moneyTransfer.setOutgoingCardNumber(card.getNumber());
//        moneyTransfer.setAmountOfMoney(card.getAmountOfMoneyOnCard());
//
//        model.put("moneyTransfer", moneyTransfer);
//        model.put("card", card);
//        model.put("userOutgoingTransfer", new User());
//        return "transfer-card";
//    }
//
//    @RequestMapping(value = "/transfer-card", method = RequestMethod.POST)
//    public String openTransferMoneyFromCardPageWithConfirmation(ModelMap model, MoneyTransfer moneyTransfer, Card card, User userOutgoingTransfer, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "transfer-card";
//        }
//
//        if (card.getAmountOfMoneyOnCard() < moneyTransfer.getAmountOfMoney()) {
//            model.put("errorMessage", "not enough money on the card");
//        } else if (!userOutgoingTransfer.getFirstName().equals("")) {
//            moneyTransferService.makeTransaction(moneyTransfer);
//            return "redirect:/list-cards";
//        }
//        long incomingCardOwner = 0;
//        try {
//            incomingCardOwner = cardService.findOwnerIdByCardNumber(moneyTransfer.getIncomingCardNumber());
//        } catch (NoSuchElementException e) {
//            model.put("errorMessage", "not such card");
//            model.put("userOutgoingTransfer", new User());
//            return "transfer-card";
//        }
//        userOutgoingTransfer = userService.findUserById(incomingCardOwner);
//        model.put("userOutgoingTransfer", userOutgoingTransfer);
//        return "transfer-card-confirm-step";
//    }


}
