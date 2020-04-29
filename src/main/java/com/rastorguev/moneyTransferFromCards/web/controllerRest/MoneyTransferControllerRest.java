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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @PostMapping("/transfer")
    public ResponseEntity<MoneyTransferDTO> transferRequest(@RequestBody MoneyTransferDTO moneyTransferDTO) throws NoSuchElement, WrongValueException, DuringOperationExecutionException {

        //TODO авторизация
        UserDTO userDTO = userService.fromUser(
                userService.findUserById(
                        cardService.findOwnerIdByCardNumber(
                                moneyTransferDTO.getOutgoingCardNumber()
                        )
                )
        );

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

        return new ResponseEntity<>(moneyTransferService.makeTransactionWithDTO(moneyTransferDTO), HttpStatus.OK);
    }

}
