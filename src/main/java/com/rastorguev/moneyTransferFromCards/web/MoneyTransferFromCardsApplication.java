package com.rastorguev.moneyTransferFromCards.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.rastorguev.moneyTransferFromCards.web.repository")
@EntityScan("com.rastorguev.moneyTransferFromCards.web.model.dto")

public class MoneyTransferFromCardsApplication {


    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferFromCardsApplication.class, args);
    }

}




