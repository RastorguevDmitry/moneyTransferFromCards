package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.dto.CardDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.Card;
import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rest/cards")
public class CardControllerRest {

    final
    ICardService cardService;

    public CardControllerRest(ICardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping
    public ResponseEntity<List<CardDTO>> getAllCards(@RequestBody UserDTO userDTO) {

        return new ResponseEntity<>((List<CardDTO>) cardService.findAllCardByOwnerIdEquals(userDTO.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/balance/{cardNumber}")
    public ResponseEntity<Float> showCardBalance(
                                                  @PathVariable Long cardNumber,
                                                   @RequestBody UserDTO userDTO) throws NoSuchElement {

        if (cardService.findOwnerIdByCardNumber(cardNumber) != userDTO.getId()) {
            throw new NoSuchElement("у вас нет такой карты " + cardNumber + cardService.findOwnerIdByCardNumber(cardNumber));
        }
        Card card = cardService.findCardByCardNumber(cardNumber);
        return new ResponseEntity<>(card.getAmountOfMoneyOnCard(), HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    public ResponseEntity<CardDTO> createNewCard(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(cardService.createCard(userDTO.getId()), HttpStatus.CREATED);
    }


    @PostMapping("/delete-card/{cardNumber}")
    public ResponseEntity<Object> deleteOneCard(
                                            @RequestBody UserDTO userDTO,
                                            @PathVariable Long cardNumber) throws NoSuchElement {
        cardService.
                deleteCard(
                        userDTO.getId(),
                        cardNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
