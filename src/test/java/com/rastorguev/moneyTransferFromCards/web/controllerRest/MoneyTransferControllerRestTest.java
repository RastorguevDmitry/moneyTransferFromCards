package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.Constants;
import com.rastorguev.moneyTransferFromCards.web.dto.MoneyTransferDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoneyTransferControllerRestTest {

    @Autowired
    private TestRestTemplate template;


    @Test
    void transferRequest() {

        ResponseEntity<MoneyTransferDTO> response = template.postForEntity("/rest/transfers/transfer", Constants.moneyTransferDTO, MoneyTransferDTO.class);
        MoneyTransferDTO moneyTransferDTO = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());

//        moneyTransferDTO.getIncomingCardNumber()

    }

//
//    @RepeatedTest(100)
//    void testConcurrentTransferConfirmation() throws InterruptedException {
//        try (RegisterUser user2 = registerRandomUser()) {
//            CardDTO cardFrom = sendCreateCardRequest(registeredUser.bearer);
//            CardDTO cardTo = sendCreateCardRequest(user2.bearer);
//
//            String s = sendDeposit(cardFrom.getCardId(), 100, registeredUser.bearer, HttpStatus.OK);
//            assertEquals(100, parseJson(s, CardDTO.class).getAmount());
//
//            List<String> transferTokens = new ArrayList<>();
//            for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
//                String transferRequestResult = sendTransferRequest(cardFrom.getCardId(), cardTo.getCardId(),
//                        100 - i, HttpStatus.OK, registeredUser.bearer);
//                VerifyTransferDTO verifyTransferDTO = parseJson(transferRequestResult, VerifyTransferDTO.class);
//                transferTokens.add(verifyTransferDTO.getToken());
//
//            }
//            CountDownLatch start = new CountDownLatch(1);
//            CountDownLatch finish = new CountDownLatch(transferTokens.size());
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            AtomicInteger successCounter = new AtomicInteger(0);
//            for (String transferToken : transferTokens) {
//                executorService.submit(() -> {
//                    try {
//                        start.await();
//                        boolean success = sendCompleteTransferNoValidate(transferToken, registeredUser.bearer);
//                        if (success) {
//                            successCounter.getAndIncrement();
//
//                        }
//
//                    } catch (Exception ex) {
//                        // ignoring exceptions
//
//                    } finally {
//                        finish.countDown();
//
//                    }
//
//                });
//
//            }
//            start.countDown();
//            finish.await();
//            System.out.println("Number of succeeded calls: " + successCounter.get());
//            CardDTO fromCard = getCard(registeredUser.bearer);
//            CardDTO toCard = getCard(user2.bearer);
//            printBalances(fromCard, toCard);
//            assertEquals(100L, fromCard.getAmount() + toCard.getAmount(), "Illegal total sum");
//
//        }
//
//    }
//
//
//    private CardDTO getCard(String bearer) {
//        ResponseEntity<String> responseEntity = executeExchange("/cards", bearer, HttpMethod.GET);
//        CardDTO[] cards = parseJson(responseEntity.getBody(), CardDTO[].class);
//        return cards[0];
//
//    }
//
//
//    private void printBalances(CardDTO fromCard, CardDTO toCard) {
//        System.out.println("\n");
//        System.out.println("Card id " + fromCard.getNumber() + ", balance: " + fromCard.getAmountOfMoneyOnCard());
//        System.out.println("Card id " + toCard.getNumber() + ", balance: " + toCard.getAmountOfMoneyOnCard());
//
//    }


}