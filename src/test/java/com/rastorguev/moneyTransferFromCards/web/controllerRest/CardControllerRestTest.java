package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.Constants;
import com.rastorguev.moneyTransferFromCards.web.dto.CardDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardControllerRestTest {

    @Autowired
    private TestRestTemplate template;



    @Test
    void createNewCardShouldReturnCreatedAndNextCard() {
        ResponseEntity<CardDTO> responseEntity = template.postForEntity("/rest/cards/create", Constants.testUserDTO, CardDTO.class);
        CardDTO actual = responseEntity.getBody();
        CardDTO expected = new CardDTO(Constants.nextCardNumberForGenerate, 0, Constants.testUserID);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(expected, actual);
    }

    @Test
    public void showAllCards() {
        ResponseEntity<Object> response = template.postForEntity("/rest/cards", Constants.testUserDTO, Object.class);
        List<CardDTO> cards = (List<CardDTO>) response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, cards.size());
    }

    @Test
    public void cardBalanceShouldReturnOkAndCorrectBalance() {
        ResponseEntity<Float> responseEntity = template.postForEntity("/rest/cards/balance/" + Constants.firstExistingCardNumberBelongUser1, Constants.testUserDTO, Float.class);
        Float actual = responseEntity.getBody();
        Float expected = 100f;
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(expected, actual);
    }

    @Test
    void deleteOneCard() {
        ResponseEntity<CardDTO> responseEntity = template
                .postForEntity(
                        "/rest/cards/delete-card/" + Constants.existingCardNumberBelongUser3,
                        Constants.testUserDTOThird,
                        CardDTO.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}