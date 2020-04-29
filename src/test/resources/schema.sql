DROP TABLE IF EXISTS User;

CREATE TABLE User (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  lastName VARCHAR(250) NOT NULL,
  firstName VARCHAR(250) NOT NULL,
  middleName VARCHAR(250) NOT NULL
);

INSERT INTO User (id, lastName, firstName, middleName) VALUES
  (1, 'lastName', 'firstName', 'middleName'),
  (2, 'lastName2', 'firstName2', 'middleName2'),
  (3, 'lastName3', 'firstName3', 'middleName3');


DROP TABLE IF EXISTS UserPrivateData;

CREATE TABLE UserPrivateData (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  login VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  ownerId BIGINT NOT NULL
);

INSERT INTO UserPrivateData (login, password, ownerId) VALUES
  ('Rastorguev', 'dima', '1'),
  ('Rastorguev2', 'dima2', '2'),
  ('Rastorguev3', 'dima3', '3');


DROP TABLE IF EXISTS Card;

CREATE TABLE Card (
  number BIGINT AUTO_INCREMENT  PRIMARY KEY,
  amountOfMoneyOnCard FLOAT NOT NULL,
  ownerId BIGINT NOT NULL
);

INSERT INTO Card (number, amountOfMoneyOnCard, ownerId) VALUES
  ('7', '100', '1'),
  ('8', '200', '1'),
  ('9', '300', '1'),
  ('10', '500', '2'),
  ('11', '0', '3');

CREATE TABLE MoneyTransfer (
  moneyTransferID INT AUTO_INCREMENT  PRIMARY KEY,
  outgoingCardNumber BIGINT ,
  incomingCardNumber BIGINT  ,
  amountOfMoney FLOAT ,
  timeToCompleteTransfer BIGINT
);


  INSERT INTO MoneyTransfer ( outgoingCardNumber, incomingCardNumber, amountOfMoney, timeToCompleteTransfer ) VALUES
    ('-10000', '7', '1000', '1587658681765'),
  ('-10000', '8', '2000', '1587658681765'),
  ('-10000', '9', '1500', '1587658681765'),
  ('-10000', '10', '700', '1587658681765'),
  ('-10000', '10', '700','1587658681765'),
  ('11', '8', '300', '1587658681765'),
  ('7', '11', '1000', '1587658681765'),
  ('10', '9', '2000', '1587665881765');
