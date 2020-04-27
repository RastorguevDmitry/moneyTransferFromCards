package com.rastorguev.moneyTransferFromCards.web.controller;

import com.rastorguev.moneyTransferFromCards.web.entity.User;
import com.rastorguev.moneyTransferFromCards.web.entity.UserPrivateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;




public class SpringBootBootstrapLiveTest {

    private static final String API_ROOT
            = "http://localhost:8080/";

    @Autowired
    private TestRestTemplate template;

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


}
