package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.Constants;
import com.rastorguev.moneyTransferFromCards.web.dto.MoneyTransferDTO;
import com.rastorguev.moneyTransferFromCards.web.entity.Card;
import com.rastorguev.moneyTransferFromCards.web.service.CardService;
import org.junit.jupiter.api.RepeatedTest;
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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoneyTransferControllerRestTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    CardService cardService;




    @RepeatedTest(20)
    void transferRequest() {

        ResponseEntity<MoneyTransferDTO> response = template.postForEntity("/rest/transfers/transfer", Constants.moneyTransferDTO, MoneyTransferDTO.class);
        MoneyTransferDTO moneyTransferDTO = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Card cardFrom = cardService.findCardByCardNumber(moneyTransferDTO.getIncomingCardNumber());
        Card cardTo = cardService.findCardByCardNumber(moneyTransferDTO.getOutgoingCardNumber());
        printBalances(cardFrom, cardTo);
        assertEquals(100L, cardFrom.getAmountOfMoneyOnCard() + cardTo.getAmountOfMoneyOnCard(), "Illegal total sum");


    }


    @RepeatedTest(10)
    void testConcurrentTransferConfirmation() throws InterruptedException {

        Card cardFrom = cardService.findCardByCardNumber(7);
        Card cardTo = cardService.findCardByCardNumber(10);

        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch finish = new CountDownLatch(4);
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger successCounter = new AtomicInteger(0);

            for (int i = 0; i < 4; i++) {

                executorService.submit(() -> {
                            try {
                                start.await();
                                MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO(cardFrom.getNumber(), cardTo.getNumber(), 1);
                                ResponseEntity<MoneyTransferDTO> response = template.postForEntity("/rest/transfers/transfer", moneyTransferDTO, MoneyTransferDTO.class);
                                assertEquals(HttpStatus.OK, response.getStatusCode());
                                boolean success = response.getStatusCode().equals(HttpStatus.OK);
                                if (success) {
                                    successCounter.getAndIncrement();

                                }

                            } catch (Exception ex) {
                                // ignoring exceptions

                            } finally {
                                finish.countDown();

                            }

                        }
                );
            }
            start.countDown();
            finish.await();
            System.out.println("Number of succeeded calls: " + successCounter.get());
            printBalances(cardFrom, cardTo);
            assertEquals(100L, cardFrom.getAmountOfMoneyOnCard() + cardTo.getAmountOfMoneyOnCard(), "Illegal total sum");

    }


    private void printBalances(Card fromCard, Card toCard) {
        System.out.println("\n");
        System.out.println("Card id " + fromCard.getNumber() + ", balance: " + fromCard.getAmountOfMoneyOnCard());
        System.out.println("Card id " + toCard.getNumber() + ", balance: " + toCard.getAmountOfMoneyOnCard());

    }


}