INSERT INTO users (id, email, name, password, phone, passport, role) VALUES (1, 'admin@cms.ru','admin', '$2a$10$8HC4.4MlKPT6uQE9bk4r5ufz1ZF77cSWL1ZTyVjFp00LN0Zaj2Gy6','+7(999)999-9999','',0);
INSERT INTO users (id, email, name, password, phone, passport, role) VALUES (2, 'test@test.ru','Тест Тестов', '$2a$10$c6vaCuFKArxAHhlpCknkvOwqvVpI5LFrxvr34z3YmGi7Ups0b6VsS','+7(123)456-7890','9988 123456',1);
INSERT INTO banks (id, name ) VALUES( 1, 'Лучший банк');
INSERT INTO banks (id, name ) VALUES( 2, 'Честный банк');
INSERT INTO banks (id, name ) VALUES( 3, 'Просто банк');

INSERT INTO offers (id, name, bank_id, lim, term, rate ) VALUES( 1, 'Лучший процент 5 лет', 1, 1000000, 60, 12);
INSERT INTO offers (id, name, bank_id, lim, term, rate ) VALUES( 2, 'Лучший процент 1 год', 1, 1000000, 12, 8);
