package com.rastorguev.moneyTransferFromCards.web.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class UserPrivateData implements Serializable {

    private static final long serialVersionUID = 1945050330541434336L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private long ownerId;

    public UserPrivateData() {
    }

    public UserPrivateData(String login, String password, long ownerId) {
        this.login = login;
        this.password = password;
        this.ownerId = ownerId;
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
