package com.rastorguev.moneyTransferFromCards.web.model.dto;

import javax.persistence.*;


@Entity
public class UserPrivateData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private long ownerId;

    public UserPrivateData() {
    }

    public UserPrivateData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
