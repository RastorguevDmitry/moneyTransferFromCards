package com.rastorguev.moneyTransferFromCards.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserPrivateDataDTO implements Serializable {

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserPrivateDataDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrivateDataDTO that = (UserPrivateDataDTO) o;
        return login.equals(that.login) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}