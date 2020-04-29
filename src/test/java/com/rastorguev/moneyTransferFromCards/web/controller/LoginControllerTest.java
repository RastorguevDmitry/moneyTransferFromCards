package com.rastorguev.moneyTransferFromCards.web.controller;

import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.entity.UserPrivateData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


class LoginControllerTest {


    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;



    private String getContextPath() {
        return BASE_URL + port ;
    }



    private User createRandomUser() {
        User user = new User();
        user.setFirstName(randomAlphabetic(10));
        user.setLastName(randomAlphabetic(10));
        user.setMiddleName(randomAlphabetic(10));
        return user;
    }

    private UserPrivateData createRandomUserPrivateData() {
        UserPrivateData userPrivateData = new UserPrivateData();
        userPrivateData.setLogin(randomAlphabetic(10));
        userPrivateData.setPassword(randomAlphabetic(10));
        return userPrivateData;
    }



//    @Test
//    void testSignIn() {
////        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(getContextPath() + "/",
////                new Card(registeredUser.login, registeredUser.password), JsonNode.class);
////        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }


    @Test
    void showLoginPage() {
    }

    @Test
    void validateUserAndOpenListCard() {
    }

    @Test
    void showSignUnPage() {
    }

    @Test
    void signUnPage() {
    }
}