--USERS
INSERT INTO user (id, first_name, last_name, document_number) VALUES (1, 'Samuel', 'Catalano', 'G418FG2Z');
INSERT INTO user (id, first_name, last_name, document_number) VALUES (2, 'Sean', 'Hederman', 'MJ871GFV4');

--ACCOUNTS
INSERT INTO account (id, sort_code, account_number, card_number, balance, user) VALUES (1, '00-05-00', 10972647, 348104591625406, 500.0, 1);
INSERT INTO account (id, sort_code, account_number, card_number, balance, user) VALUES (2, '12-00-34', 33972913, 499106513548023, 400.0, 2);