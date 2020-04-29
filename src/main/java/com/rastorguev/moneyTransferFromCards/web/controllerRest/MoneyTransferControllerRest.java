package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.dto.CardDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.MoneyTransferDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
import com.rastorguev.moneyTransferFromCards.web.exceptions.DuringOperationExecutionException;
import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.exceptions.WrongValueException;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IMoneyTransferService;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.rastorguev.moneyTransferFromCards.web.constants.Constants.THIRD_PARTY_SOURS_FOU_TRANSACTION;

@Controller
@RequestMapping("/rest/transfers")
public class MoneyTransferControllerRest {

    final
    ICardService cardService;
    final
    IUserService userService;
    final
    IMoneyTransferService moneyTransferService;

    public MoneyTransferControllerRest(ICardService cardService, IUserService userService, IMoneyTransferService moneyTransferService) {
        this.cardService = cardService;
        this.userService = userService;
        this.moneyTransferService = moneyTransferService;
    }

    @PutMapping(value = "/transfer-card")
    public String makeTransfer(UserDTO user, @PathVariable Long cardNumber) {
        moneyTransferService.makeIncomingTransactionWithThirdPartySource(cardNumber, 500, THIRD_PARTY_SOURS_FOU_TRANSACTION);
        return "redirect:/list-cards";
    }

    @PutMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public MoneyTransferDTO transferRequest(UserDTO userDTO,
                                            @RequestBody MoneyTransferDTO moneyTransferDTO) throws NoSuchElement, WrongValueException, DuringOperationExecutionException {

        CardDTO outgoingCard = cardService
                .fromCard(
                        cardService
                                .findCardByCardNumber(
                                        moneyTransferDTO
                                                .getOutgoingCardNumber()
                                )
                );

        if (userDTO.getId() != outgoingCard.getOwnerId()) {
            throw new NoSuchElement("у вас нет такой карты");
        }
        if (outgoingCard.getAmountOfMoneyOnCard() < moneyTransferDTO.getAmountOfMoney()) {
            throw new WrongValueException("у вас не достаточно денег на счету");
        }

        long incomingCardOwnerID = cardService
                .findOwnerIdByCardNumber(
                        moneyTransferDTO
                                .getIncomingCardNumber()
                );

        UserDTO incomingCardUserDTO = userService
                .fromUser(
                        userService
                                .findUserById(
                                        incomingCardOwnerID)
                );




        return moneyTransferService.makeTransactionWithDTO(moneyTransferDTO);
    }




}
