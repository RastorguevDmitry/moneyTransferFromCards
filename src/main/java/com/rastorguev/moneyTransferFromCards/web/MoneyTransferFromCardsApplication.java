package com.rastorguev.moneyTransferFromCards.web;

import com.rastorguev.moneyTransferFromCards.web.model.dto.Card;
import com.rastorguev.moneyTransferFromCards.web.model.dto.MoneyTransfer;
import com.rastorguev.moneyTransferFromCards.web.model.dto.User;
import com.rastorguev.moneyTransferFromCards.web.model.dto.UserPrivateData;
import com.rastorguev.moneyTransferFromCards.web.repository.CardRepository;
import com.rastorguev.moneyTransferFromCards.web.service.MoneyTransferService;
import com.rastorguev.moneyTransferFromCards.web.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.rastorguev.moneyTransferFromCards.web.constants.Constants.THIRD_PARTY_SOURS_FOU_TRANSACTION;

@SpringBootApplication
@EnableJpaRepositories("com.rastorguev.moneyTransferFromCards.web.repository")
@EntityScan("com.rastorguev.moneyTransferFromCards.web.model.dto")

public class MoneyTransferFromCardsApplication {


    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferFromCardsApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UserService userService, CardRepository cardRepository, MoneyTransferService moneyTransferService) {
        return (args) -> {
            User user1 = userService.createNewUser(new User("Dmitry", "Rastorguev", "Ivanovich"),
                    new UserPrivateData("Rastorguev", "dima"));
            User user2 = userService.createNewUser(new User("Dmitry2", "Rastorguev2", "Ivanovich2"),
                    new UserPrivateData("Rastorguev2", "dima2"));
            User user3 = userService.createNewUser(new User("Dmitry3", "Rastorguev3", "Ivanovich3"),
                    new UserPrivateData("Rastorguev3", "dima3"));

            Card card1 = cardRepository.save(new Card(user1.getId()));
            Card card2 = cardRepository.save(new Card(user1.getId()));
            Card card3 = cardRepository.save(new Card(user1.getId()));
            Card card4 = cardRepository.save(new Card(user2.getId()));
            Card card5 = cardRepository.save(new Card(user3.getId()));

            moneyTransferService.makeIncomingTransactionWithThirdPartySource(card1.getNumber(), 1500, THIRD_PARTY_SOURS_FOU_TRANSACTION);
            moneyTransferService.makeIncomingTransactionWithThirdPartySource(card2.getNumber(), 2500, THIRD_PARTY_SOURS_FOU_TRANSACTION);
            moneyTransferService.makeIncomingTransactionWithThirdPartySource(card3.getNumber(), 1500, THIRD_PARTY_SOURS_FOU_TRANSACTION);
            moneyTransferService.makeIncomingTransactionWithThirdPartySource(card4.getNumber(), 1500, THIRD_PARTY_SOURS_FOU_TRANSACTION);
            moneyTransferService
                    .makeTransaction(
                            new MoneyTransfer(
                                    card4.getNumber(),
                                    card2.getNumber(),
                                    500,
                                    System.currentTimeMillis() - 1_000 * 60 * 60 * 24 * 20
                            ));

            moneyTransferService
                    .makeTransaction(
                            new MoneyTransfer(
                                    card4.getNumber(),
                                    card2.getNumber(),
                                    800,
                                    System.currentTimeMillis() - 1_000 * 60 * 60 * 24 * 10
                            ));

            moneyTransferService
                    .makeTransaction(
                            new MoneyTransfer(
                                    card3.getNumber(),
                                    card2.getNumber(),
                                    900,
                                    System.currentTimeMillis() - 1_000 * 60 * 60 * 24 * 5
                            ));

        };
    }

}




