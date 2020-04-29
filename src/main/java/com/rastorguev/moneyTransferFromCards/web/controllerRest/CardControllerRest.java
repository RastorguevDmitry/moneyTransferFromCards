package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.dto.CardDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
import com.rastorguev.moneyTransferFromCards.web.exceptions.NoSuchElement;
import com.rastorguev.moneyTransferFromCards.web.service.interfaces.ICardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/cards")
public class CardControllerRest {

    final
    ICardService cardService;

    public CardControllerRest(ICardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping
    public List<CardDTO> getAllCards(UserDTO userDTO) {
        return (List<CardDTO>) cardService.findAllCardByOwnerIdEquals(userDTO.getId());
    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO createNewCard(UserDTO userDTO) {
        return cardService.createCard(userDTO.getId());
    }


    @DeleteMapping("/delete-card/{cardNumber}")
    public void deleteOneCards(UserDTO user, @PathVariable Long cardNumber) throws NoSuchElement {
        cardService.
                deleteCard(
                        user.getId(),
                        cardNumber);
    }
}
