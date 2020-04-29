package com.rastorguev.moneyTransferFromCards.web.dto;

import java.io.Serializable;

public class UserRegisterDTO implements Serializable {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String middleName;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public UserRegisterDTO(String login, String password, String firstName, String lastName, String middleName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

}