package com.rastorguev.moneyTransferFromCards.web.controllerRest;

import com.rastorguev.moneyTransferFromCards.web.Constants;
import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
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
class LoginControllerRestTest {

    @Autowired
    private TestRestTemplate template;


    @Test
    void validateUser() {
        ResponseEntity<UserDTO>  response = template.postForEntity("/rest/signin", Constants.userPrivateDataDTO, UserDTO.class);
        UserDTO userDTO =  response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, Constants.testUserDTO);
    }

    @Test
    void signUpUser() {
        ResponseEntity<UserDTO>  response = template.postForEntity("/rest/sign-up", Constants.userRegisterDTO, UserDTO.class);
        UserDTO userDTO =  response.getBody();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals( Constants.userDTONewUser, userDTO);
    }
}