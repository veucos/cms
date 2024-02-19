insert into users(id, email, name, password, phone, passport, role) values
(1001, 'user1@test.ru','User1', '$2a$10$8HC4.4MlKPT6uQE9bk4r5ufz1ZF77cSWL1ZTyVjFp00LN0Zaj2Gy6','+7(999)999-9999','',0),
(1002, 'user2@test.ru','User2', '$2a$10$c6vaCuFKArxAHhlpCknkvOwqvVpI5LFrxvr34z3YmGi7Ups0b6VsS','+7(123)456-7890','9988 123456',1),
(1003, 'user3@test.ru','User3', '$2a$10$c6vaCuFKArxAHhlpCknkvOwqvVpI5LFrxvr34z3YmGi7Ups0b6VsS','+7(123)456-7890','9988 123456',1);

insert into banks(id, name) values
(1001, 'Bank1'),
(1002, 'Bank2'),
(1003, 'Bank3');

INSERT INTO offers (id, name, bank_id, lim, term, rate ) VALUES
(1001, 'Offer1', 1001, 1000000, 60, 12),
(1002, 'Offer2', 1001, 1000000, 12, 8),
(1003, 'Offer3', 1002, 1000000, 12, 10);

insert into credits (id, user_id, offer_id, amount, date) values
(1001, 1001,1001,10000,'2024-02-18'),
(1002, 1001,1002,20000,'2024-02-18'),
(1003, 1002,1001,30000,'2024-02-18');
