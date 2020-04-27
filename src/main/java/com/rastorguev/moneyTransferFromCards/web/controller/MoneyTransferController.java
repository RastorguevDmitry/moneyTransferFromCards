package com.rastorguev.moneyTransferFromCards.web.controller;


import com.rastorguev.moneyTransferFromCards.web.entity.Card;
import com.rastorguev.moneyTransferFromCards.web.entity.MoneyTransfer;
import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IMoneyTransferService;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.NoSuchElementException;

import static com.rastorguev.moneyTransferFromCards.web.constants.Constants.THIRD_PARTY_SOURS_FOU_TRANSACTION;

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

    @RequestMapping(value = "/topup-card-balance", method = RequestMethod.GET)
    public String topUpBalanceCardByNumber(@RequestParam long number) {
        moneyTransferService.makeIncomingTransactionWithThirdPartySource(number, 500, THIRD_PARTY_SOURS_FOU_TRANSACTION);
        return "redirect:/list-cards";
    }

    @RequestMapping(value = "/transfer-card", method = RequestMethod.GET)
    public String openTransferMoneyFromCardPage(@RequestParam long number, ModelMap model) {

        Card card = cardService.findCardByCardNumber(number);

        MoneyTransfer moneyTransfer = new MoneyTransfer();
        moneyTransfer.setOutgoingCardNumber(card.getNumber());
        moneyTransfer.setAmountOfMoney(card.getAmountOfMoneyOnCard());

        model.put("moneyTransfer", moneyTransfer);
        model.put("card", card);
        model.put("userOutgoingTransfer", new User());
        return "transfer-card";
    }

    @RequestMapping(value = "/transfer-card", method = RequestMethod.POST)
    public String openTransferMoneyFromCardPageWithConfirmation(ModelMap model, MoneyTransfer moneyTransfer, Card card, User userOutgoingTransfer, BindingResult result) {

        if (result.hasErrors()) {
            return "transfer-card";
        }

        if (card.getAmountOfMoneyOnCard() < moneyTransfer.getAmountOfMoney()) {
            model.put("errorMessage", "not enough money on the card");
        } else if (!userOutgoingTransfer.getFirstName().equals("")) {
            moneyTransferService.makeTransaction(moneyTransfer);
            return "redirect:/list-cards";
        }
        long incomingCardOwner = 0;
        try {
            incomingCardOwner = cardService.findOwnerIdByCardNumber(moneyTransfer.getIncomingCardNumber());
        } catch (NoSuchElementException e) {
            model.put("errorMessage", "not such card");
            model.put("userOutgoingTransfer", new User());
            return "transfer-card";
        }
        userOutgoingTransfer = userService.findUserById(incomingCardOwner);
        model.put("userOutgoingTransfer", userOutgoingTransfer);
        return "transfer-card-confirm-step";
    }


    @RequestMapping(value = "/history-of-operation-findAllByAmountOfMoneyBetween", method = RequestMethod.GET)
    public String openHistoryOfOperationPageFindAllByAmountOfMoneyBetween(
            ModelMap model,
            @RequestParam float amountOfMoneyFrom,
            @RequestParam float amountOfMoneyTo) {

        User user = (User) model.get("currentUser");

        model.put("transfers",
                moneyTransferService
                        .findAllByOwnerAndAmountOfMoneyBetween(
                                user,
                                amountOfMoneyFrom,
                                amountOfMoneyTo));
        model.put("title", "findAllByAmountOfMoneyBetween" + amountOfMoneyFrom + " and " + amountOfMoneyTo);

        return "history-of-operation";
    }

    @RequestMapping(value = "/history-of-operation-findAllByIncomingCardNumberIs", method = RequestMethod.GET)
    public String openHistoryOfOperationPageFindAllByIncomingCardNumberIs(
            ModelMap model,
            @RequestParam long incomingCardNumber) {

        User user = (User) model.get("currentUser");
        model.put("transfers",
                moneyTransferService
                        .findAllByIncomingCardNumberIs(
                                user,
                                incomingCardNumber
                        ));
        model.put("title", "findAllByIncomingCardNumberIs" + incomingCardNumber);

        return "history-of-operation";
    }


    @RequestMapping(value = "/history-of-operation-findAllOutgoingOperationByTimeToCompleteTransferBetween", method = RequestMethod.GET)
    public String openHistoryOfOutgoingOperationPageFindAllByTimeToCompleteTransferBetween(
            ModelMap model,
            @RequestParam long timeToCompleteTransferFrom,
            @RequestParam long timeToCompleteTransferTo) {
        User user = (User) model.get("currentUser");
        model.put("transfers",
                moneyTransferService
                        .findAllOutgoingTransferByTimeToCompleteTransferBetween(
                                user,
                                timeToCompleteTransferFrom,
                                timeToCompleteTransferTo
                        ));
        model.put("title", "findAllOutgoingTransferByTimeToCompleteTransferBetween");
        return "history-of-operation";
    }

    @RequestMapping(value = "/history-of-operation-findAllOutgoingOperationByTimeToCompleteTransferLastTwoHours", method = RequestMethod.GET)
    public String openHistoryOfOperationPageFindAllOutgoingOperationByTimeToCompleteTransferLastTwoHours(ModelMap model) {
        return "redirect:/history-of-operation-findAllOutgoingOperationByTimeToCompleteTransferBetween" +
                "?timeToCompleteTransferFrom=" + (System.currentTimeMillis() - 2L * 60 * 60 * 1000) +
                "&timeToCompleteTransferTo=" + System.currentTimeMillis();
    }



    //▪	Сумма переводов в разрезе счетов получателей за период
    @RequestMapping(value = "/history-of-operation-findSumOfTransfersByIncomingAccountsForLast30Days", method = RequestMethod.GET)
    public String findSumOfTransfersByIncomingAccountsForLast30Days(
            ModelMap model) {
        User user = (User) model.get("currentUser");

        model.put("transfers", moneyTransferService.findSumOfTransfersByIncomingAccountsForPeriod(
                user,
                System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000,
                System.currentTimeMillis()));

        //не выводит
        return "history-of-operation";
    }

    //▪	Средние траты в день за период
    @RequestMapping(value = "/history-of-operation-averageSpendingPerDayForLast30Days", method = RequestMethod.GET)
    public String averageSpendingPerDayForLast30Days(
            ModelMap model) {
        User user = (User) model.get("currentUser");

        long timeToCompleteTransferFrom = System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000;
        long timeToCompleteTransferTo = System.currentTimeMillis();

        model.put("value", moneyTransferService
                .averageSpendingPerDayForPeriod(
                        user,
                        timeToCompleteTransferFrom,
                        timeToCompleteTransferTo));
        model.put("title", "averageSpendingPerDayForLast30Days");
        return "history-of-operation-one-value";
    }

    //▪	Отношение поступлений к тратам ForLast30Days
    @RequestMapping(value = "/history-of-operation-ratioIncomingToOutgoingTransfersForLast30Days", method = RequestMethod.GET)
    public String ratioIncomingToOutgoingTransfersForLast30Days(
            ModelMap model) {
        User user = (User) model.get("currentUser");

        long timeToCompleteTransferFrom = System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000;
        long timeToCompleteTransferTo = System.currentTimeMillis();

        Float ratioIncomingToOutgoingTransfers =
                moneyTransferService
                        .ratioIncomingToOutgoingTransfers(
                                user,
                                timeToCompleteTransferFrom,
                                timeToCompleteTransferTo);

        model.put("value", ratioIncomingToOutgoingTransfers);
        model.put("title", "ratioIncomingToOutgoingTransfersForLast30Days");
        return "history-of-operation-one-value";
    }

    //▪	Операция с максимальной суммой за период
    @RequestMapping(value = "/history-of-operation-operationWithMaximumAmountForPeriod", method = RequestMethod.GET)
    public String operationWithMaximumAmountForPeriod(
            ModelMap model,
            @RequestParam long timeToCompleteTransferFrom,
            @RequestParam long timeToCompleteTransferTo) {

        User user = (User) model.get("currentUser");
        Float operationWithMaximumAmountForPeriod =
                moneyTransferService
                        .operationWithMaximumAmountForPeriod(
                                user,
                                timeToCompleteTransferFrom,
                                timeToCompleteTransferTo);

        model.put("value", operationWithMaximumAmountForPeriod);
        model.put("title", "operationWithMaximumAmountForPeriod");
        return "history-of-operation-one-value";
    }

    //▪	Операция с максимальной суммой за период 30 дней
    @RequestMapping(value = "/history-of-operation-operationWithMaximumAmountForLast30Days", method = RequestMethod.GET)
    public String operationWithMaximumAmountForLast30Days(ModelMap model) {
        return "redirect:/history-of-operation-operationWithMaximumAmountForPeriod" +
                "?timeToCompleteTransferFrom=" + (System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000) +
                "&timeToCompleteTransferTo=" + System.currentTimeMillis();
    }
}
