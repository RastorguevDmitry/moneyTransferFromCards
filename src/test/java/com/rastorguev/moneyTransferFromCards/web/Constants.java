package com.rastorguev.moneyTransferFromCards.web;

import com.rastorguev.moneyTransferFromCards.web.dto.MoneyTransferDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserPrivateDataDTO;
import com.rastorguev.moneyTransferFromCards.web.dto.UserRegisterDTO;

public class Constants {

    public static int testUserID = 1;
    public static int nextCardNumberForGenerate = 12;
    public static int firstExistingCardNumberBelongUser1 = 7;
    public static int secondExistingCardNumberBelongUser1 = 8;
    public static int existingCardNumberBelongUser3 = 11;
    public static UserDTO testUserDTO = new UserDTO(testUserID, "lastName", "firstName", "middleName");
    public static UserDTO testUserDTOSecond = new UserDTO(2, "lastName2", "firstName2", "middleName2");
    public static UserDTO testUserDTOThird = new UserDTO(3, "lastName3", "firstName3", "middleName3");

    public static UserPrivateDataDTO userPrivateDataDTO = new UserPrivateDataDTO("Rastorguev", "dima");
    public static UserPrivateDataDTO userPrivateDataDTONewUser = new UserPrivateDataDTO("Rastorguev4", "dima4");
    public static UserRegisterDTO userRegisterDTO = new UserRegisterDTO("Rastorguev4", "dima4", "firstName", "lastName", "middleName");
    public static UserDTO userDTONewUser = new UserDTO( 4,"lastName", "firstName", "middleName");
    public static MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO(7, 10, 1);

}
